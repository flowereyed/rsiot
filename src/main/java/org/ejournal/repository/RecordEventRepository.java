package org.ejournal.repository;

import org.ejournal.model.RecordEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordEventRepository extends JpaRepository<RecordEvent, Long> {
}
