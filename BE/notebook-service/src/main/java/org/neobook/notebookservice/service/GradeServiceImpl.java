package org.neobook.notebookservice.service;

import org.neobook.notebookservice.dto.CreateGradeDto;
import org.neobook.notebookservice.dto.GradeDto;
import org.neobook.notebookservice.dto.UpdateGradeDto;
import org.neobook.notebookservice.mapper.GradeMapper;
import org.neobook.notebookservice.model.Grade;
import org.neobook.notebookservice.repository.GradeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository repository;
    private final GradeMapper mapper;

    public GradeServiceImpl(GradeRepository repository, GradeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Flux<GradeDto> getGradesByStudent(UUID studentId) {
        return repository.findAllByStudentId(studentId)
                .map(mapper::toDto);
    }

    @Override
    public Mono<GradeDto> create(CreateGradeDto dto) {
        Grade entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDto);
    }

    @Override
    public Mono<GradeDto> update(String id, UpdateGradeDto dto) {
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
