package org.ejournal.publisher.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ejournal.config.PubSubConfig;
import org.ejournal.model.RecordEvent;
import org.ejournal.publisher.RecordEventPublisher;
import org.ejournal.repository.RecordEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PubSubRecordEventPublisherImpl implements RecordEventPublisher {

    private final PubSubConfig.GCPEventSender eventSender;
    private final RecordEventRepository eventRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PubSubRecordEventPublisherImpl(
            PubSubConfig.GCPEventSender eventSender,
            RecordEventRepository eventRepository,
            ObjectMapper objectMapper
    ) {
        this.eventSender = eventSender;
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(RecordEvent recordEvent) {
        try {
            eventRepository.saveAndFlush(recordEvent);
            eventSender.sendToPubsub(
                    objectMapper.writeValueAsString(recordEvent)
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
