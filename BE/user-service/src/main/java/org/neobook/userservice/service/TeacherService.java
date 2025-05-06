package org.neobook.userservice.service;

import org.neobook.userservice.dto.CreateTeacherDto;
import org.neobook.userservice.dto.TeacherDto;
import org.neobook.userservice.dto.UpdateTeacherDto;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    List<TeacherDto> findAll();
    TeacherDto findByKeycloakUserId(UUID keycloakUserId);
    TeacherDto create(CreateTeacherDto request);
    TeacherDto update(UUID kcId, UpdateTeacherDto dto);
    void delete(UUID keycloakUserId);
}

