package org.ejournal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Long id;

    @Column(name ="date")
    private LocalDateTime dateTime = LocalDateTime.now();

    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(name ="mark")
    private Mark mark;

    @ManyToOne
    @JoinColumn(name="journal_id", nullable=false)
    @JsonIgnore
    private Journal journal;

    @Column(name ="isExam")
    private boolean isExamMark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Boolean getExamMark() {
        return isExamMark;
    }

    public void setIsExamMark(Boolean examMark) {
        isExamMark = examMark;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isExamMark() {
        return isExamMark;
    }

    public void setExamMark(boolean examMark) {
        isExamMark = examMark;
    }

}
