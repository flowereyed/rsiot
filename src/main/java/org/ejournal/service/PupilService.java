package org.ejournal.service;

import org.ejournal.model.Pupil;
import org.ejournal.repository.PupilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PupilService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PupilService.class);

    private final PupilRepository pupilRepository;

    @Autowired
    public PupilService(PupilRepository pupilRepository) {
        this.pupilRepository = pupilRepository;
    }

    public void updatePupil(Pupil pupil) {

        LOGGER.info("Update pupil with id: {}", pupil.getId());

        if (Objects.nonNull(pupil.getId())) {
            pupilRepository.save(pupil);
        } else {
            throw new IllegalArgumentException("This pupil is not in system!");
        }
    }

    public void deleteById(Long pupilId) {

        LOGGER.info("Delete pupil with id: {}", pupilId);

        pupilRepository.deleteById(pupilId);
    }
}
