package org.neobook.notebookservice.service;

import org.neobook.notebookservice.dto.CreateGradeDto;
import org.neobook.notebookservice.dto.GradeDto;
import org.neobook.notebookservice.dto.UpdateGradeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GradeService {
    Flux<GradeDto> getGradesByStudent(UUID studentId);
    Mono<GradeDto> create(CreateGradeDto dto);
    Mono<GradeDto> update(String id, UpdateGradeDto dto);
    Mono<Void> delete(String id);
}
