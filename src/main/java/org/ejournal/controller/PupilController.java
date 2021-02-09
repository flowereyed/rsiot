package org.ejournal.controller;

import org.ejournal.model.Pupil;
import org.ejournal.service.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pupils")
public class PupilController {

    private final PupilService pupilService;

    @Autowired
    public PupilController(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @PutMapping
    public void updatePupil(Pupil pupil) {
        pupilService.updatePupil(pupil);
    }

    @DeleteMapping("/{pupilId}")
    public void deletePupilById(@PathVariable Long pupilId) {
        pupilService.deleteById(pupilId);
    }
}
