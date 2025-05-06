package org.neobook.userservice.service;

import org.neobook.userservice.dto.CreateStudentDto;
import org.neobook.userservice.dto.StudentDto;
import org.neobook.userservice.dto.UpdateStudentDto;
import org.neobook.userservice.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<StudentDto> findAll();
    StudentDto findByKeycloakUserId(UUID keycloakUserId);
    StudentDto create(CreateStudentDto request);
    StudentDto update(UUID keycloakUserId, UpdateStudentDto request);
    void delete(UUID keycloakUserId);
    Student getEntityByKeycloakUserId(UUID keycloakUserId);
    StudentDto updateParents(UUID studentKeycloakUserId, List<UUID> parentKeycloakUserIds);
}

