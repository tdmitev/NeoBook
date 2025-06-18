package org.neobook.notebookservice.repository;

import org.neobook.notebookservice.model.Absence;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface AbsenceRepository extends ReactiveMongoRepository<Absence, String> {
    Flux<Absence> findAllByStudentId(UUID studentId);
}
