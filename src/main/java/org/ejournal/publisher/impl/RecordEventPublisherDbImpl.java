package org.ejournal.publisher.impl;

import org.ejournal.model.RecordEvent;
import org.ejournal.publisher.RecordEventPublisher;
import org.ejournal.repository.RecordEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecordEventPublisherDbImpl implements RecordEventPublisher {


    private final RecordEventRepository eventRepository;

    @Autowired
    public RecordEventPublisherDbImpl(RecordEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void publish(RecordEvent recordEvent) {
        eventRepository.save(recordEvent);
    }
}
