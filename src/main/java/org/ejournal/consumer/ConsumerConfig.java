package org.ejournal.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ejournal.model.RecordEvent;
import org.ejournal.repository.RecordEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class ConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerConfig.class);

    private final ObjectMapper objectMapper;
    private final RecordEventRepository eventRepository;

    @Autowired
    public ConsumerConfig(ObjectMapper objectMapper, RecordEventRepository eventRepository) {
        this.objectMapper = objectMapper;
        this.eventRepository = eventRepository;
    }

    @Bean
    public PubSubInboundChannelAdapter channelAdapter(
            @Qualifier("inputChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {

        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(
                        pubSubTemplate,
                        "ejournal_sub_callback"
                );

        adapter.setOutputChannel(inputChannel);
        adapter.setPayloadType(String.class);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "inputChannel")
    public MessageHandler messageReceiver() {
        return message -> {
            try {
                final RecordEvent event = objectMapper.
                        readValue(message.getPayload().toString(),
                                RecordEvent.class);

                BasicAcknowledgeablePubsubMessage originalMessage =
                        message.getHeaders().get(
                                GcpPubSubHeaders.ORIGINAL_MESSAGE,
                                BasicAcknowledgeablePubsubMessage.class
                        );
                originalMessage.ack();

                eventRepository.save(event);

            } catch (JsonProcessingException e) {
                LOGGER.error("Exception during processing", e);
            }
        };
    }
}
