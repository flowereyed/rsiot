package org.ejournal.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RecordEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pupilEmail;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private ExamStatus status;

    private String subject;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPupilEmail() {
        return pupilEmail;
    }

    public void setPupilEmail(String pupilEmail) {
        this.pupilEmail = pupilEmail;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ExamStatus getStatus() {
        return status;
    }

    public void setStatus(ExamStatus status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
