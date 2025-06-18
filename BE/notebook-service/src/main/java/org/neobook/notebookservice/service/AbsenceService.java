package org.neobook.notebookservice.service;

import org.neobook.notebookservice.dto.AbsenceDto;
import org.neobook.notebookservice.dto.CreateAbsenceDto;
import org.neobook.notebookservice.dto.UpdateAbsenceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AbsenceService {
    Flux<AbsenceDto> getAbsencesByStudent(UUID studentId);
    Mono<AbsenceDto> create(CreateAbsenceDto dto);
    Mono<AbsenceDto> update(String id, UpdateAbsenceDto dto);
    Mono<Void> delete(String id);
}
