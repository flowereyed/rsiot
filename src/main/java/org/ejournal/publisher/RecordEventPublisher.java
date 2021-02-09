package org.ejournal.publisher;


import org.ejournal.model.RecordEvent;

public interface RecordEventPublisher {

    void publish(RecordEvent recordEvent);
}
