package org.ejournal.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RecordEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long pupilId;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private ExamStatus status;

    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPupilId() {
        return pupilId;
    }

    public void setPupilId(Long pupilId) {
        this.pupilId = pupilId;
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
}
