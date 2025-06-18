package org.neobook.notebookservice.repository;

import org.neobook.notebookservice.model.Grade;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface GradeRepository extends ReactiveMongoRepository<Grade, String> {
    Flux<Grade> findAllByStudentId(UUID studentId);
}
