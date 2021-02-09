package org.ejournal.controller;

import org.ejournal.model.Journal;
import org.ejournal.model.Pupil;
import org.ejournal.model.Record;
import org.ejournal.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {

    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public List<Journal> getAllJournals() {
        return journalService.findAll();
    }

    @GetMapping("/{journalId}")
    public Journal getJournalById(@PathVariable Long journalId) {
        return journalService.findById(journalId);
    }

    @PostMapping
    public void createByPupil(@RequestBody Pupil pupil) {
        journalService.create(pupil);
    }

    @PostMapping("/{journalId}/records")
    public void createByPupil(@RequestBody Record record,
                              @PathVariable Long journalId) {
        journalService.addRecordById(record, journalId);
    }
}
