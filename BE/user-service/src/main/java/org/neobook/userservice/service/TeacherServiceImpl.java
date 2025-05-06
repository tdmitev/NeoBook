package org.neobook.userservice.service;


import org.neobook.userservice.dto.CreateTeacherDto;
import org.neobook.userservice.dto.UpdateProfileDto;
import org.neobook.userservice.dto.UpdateTeacherDto;
import org.neobook.userservice.exception.ResourceNotFoundException;
import org.neobook.userservice.mapper.TeacherMapper;
import org.neobook.userservice.model.Teacher;
import org.neobook.userservice.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.neobook.userservice.dto.TeacherDto;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repo;
    private final TeacherMapper mapper;
    private final KeycloakService keycloakService;

    public TeacherServiceImpl(TeacherRepository repo, TeacherMapper mapper, KeycloakService keycloakService) {
        this.repo = repo;
        this.mapper = mapper;
        this.keycloakService = keycloakService;
    }

    @Override
    public List<TeacherDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public TeacherDto findByKeycloakUserId(UUID keycloakUserId) {
        Teacher entity = repo.findByKeycloakUserId(keycloakUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + keycloakUserId));
        return mapper.toDto(entity);
    }

    @Override
    public TeacherDto create(CreateTeacherDto req) {
        if (repo.existsByKeycloakUserId(req.keycloakUserId())) {
            throw new IllegalArgumentException("Teacher exists: " + req.keycloakUserId());
        }
        keycloakService.assignRealmRole(req.keycloakUserId(), "ROLE_TEACHER");
        Teacher ent = mapper.toEntity(req);
        repo.save(ent);
        return mapper.toDto(ent);
    }

    @Override
    public TeacherDto update(UUID kcId, UpdateTeacherDto dto) {
        Teacher ent = repo.findByKeycloakUserId(kcId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + kcId));
        mapper.updateEntityFromDto(dto, ent);
        repo.save(ent);
        return mapper.toDto(ent);
    }

    @Override
    public void delete(UUID keycloakUserId) {
        Teacher entity = repo.findByKeycloakUserId(keycloakUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + keycloakUserId));
        repo.delete(entity);
    }
}
