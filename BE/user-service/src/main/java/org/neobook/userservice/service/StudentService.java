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

    List<StudentDto> findAllBySchoolClassId(Long classId);

    void assignStudentToClass(UUID studentKeycloakUserId, Long classId);

    void unassignStudentFromClass(UUID studentKeycloakUserId, Long classId);

    void clearClassForStudents(List<UUID> studentKeycloakUserIds);

    void clearClassReference(Long classId);
}

