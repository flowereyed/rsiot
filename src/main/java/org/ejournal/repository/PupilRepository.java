package org.ejournal.repository;

import org.ejournal.model.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PupilRepository extends JpaRepository<Pupil, Long> {

    Optional<Pupil> findByEmail(String email);
}
