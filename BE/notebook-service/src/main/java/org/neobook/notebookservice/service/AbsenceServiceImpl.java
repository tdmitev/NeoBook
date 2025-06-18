package org.neobook.notebookservice.service;

import org.neobook.notebookservice.dto.AbsenceDto;
import org.neobook.notebookservice.dto.CreateAbsenceDto;
import org.neobook.notebookservice.dto.UpdateAbsenceDto;
import org.neobook.notebookservice.mapper.AbsenceMapper;
import org.neobook.notebookservice.model.Absence;
import org.neobook.notebookservice.repository.AbsenceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository repository;
    private final AbsenceMapper mapper;

    public AbsenceServiceImpl(AbsenceRepository repository, AbsenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Flux<AbsenceDto> getAbsencesByStudent(UUID studentId) {
        return repository.findAllByStudentId(studentId)
                .map(mapper::toDto);
    }

    @Override
    public Mono<AbsenceDto> create(CreateAbsenceDto dto) {
        Absence entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDto);
    }

    @Override
    public Mono<AbsenceDto> update(String id, UpdateAbsenceDto dto) {
        return repository.findById(id)
                .flatMap(existing -> {
                    mapper.updateEntity(dto, existing);
                    return repository.save(existing);
                })
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
