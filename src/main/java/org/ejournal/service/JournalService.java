package org.ejournal.service;

import org.ejournal.model.*;
import org.ejournal.publisher.RecordEventPublisher;
import org.ejournal.repository.JournalRepository;
import org.ejournal.repository.PupilRepository;
import org.ejournal.repository.RecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class JournalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JournalService.class);

    private final JournalRepository journalRepository;
    private final PupilRepository pupilRepository;
    private final RecordRepository recordRepository;
    private final RecordEventPublisher eventPublisher;

    @Autowired
    public JournalService(JournalRepository journalRepository,
                          PupilRepository pupilRepository,
                          RecordRepository recordRepository,
                          RecordEventPublisher eventPublisher) {
        this.journalRepository = journalRepository;
        this.pupilRepository = pupilRepository;
        this.recordRepository = recordRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Journal> findAll() {

        LOGGER.info("Find all journals");

        return journalRepository.findAll();
    }

    public Journal findById(Long journalId) {

        LOGGER.info("Find journal with id: {}", journalId);

        return journalRepository.findById(journalId)
                .orElseThrow(() -> new EntityNotFoundException("Journal not found"));
    }

    public Long create(Pupil pupil) {

        LOGGER.info("Find journal for pupil with email: {}", pupil.getEmail());

        Pupil currentPupil = pupilRepository.findByEmail(pupil.getEmail())
                .orElse(pupilRepository.save(pupil));

        Journal journal = new Journal();
        journal.setPupil(currentPupil);

        return journalRepository.save(journal).getId();
    }

    public Long addRecordById(Record record, Long journalId) {

        LOGGER.info("Add record to journal with id: {}", journalId);

        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new EntityNotFoundException("Journal not found"));

        record.setJournal(journal);
        recordRepository.saveAndFlush(record);

        if (record.getExamMark()){
            RecordEvent event = new RecordEvent();
            event.setPupilEmail(journal.getPupil().getEmail());
            event.setDateTime(record.getDateTime());
            event.setSubject(record.getSubject());
            event.setEventStatus(EventStatus.IN_PROGRESS);

            if (record.getMark().getValue() >= Mark.FOUR.getValue()){
                event.setStatus(ExamStatus.SUCCESS);
            } else {
                event.setStatus(ExamStatus.RETAKE);
            }

            eventPublisher.publish(event);
        }

        return record.getId();
    }

}
