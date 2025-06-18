package org.neobook.userservice.service;


import org.neobook.userservice.exception.ResourceNotFoundException;
import org.neobook.userservice.mapper.StudentMapper;
import org.neobook.userservice.model.Parent;
import org.neobook.userservice.model.Student;
import org.neobook.userservice.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.neobook.userservice.dto.CreateStudentDto;
import org.neobook.userservice.dto.StudentDto;
import org.neobook.userservice.dto.UpdateStudentDto;
import org.neobook.userservice.service.SchoolServiceClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repo;
    private final StudentMapper mapper;
    private final SharedLookupService lookup;
    private final KeycloakService keycloakService;
    private final SchoolServiceClient schoolClient;

    public StudentServiceImpl(StudentRepository repo,
                              StudentMapper mapper,
                              SharedLookupService lookup,
                              KeycloakService keycloakService,
                              SchoolServiceClient schoolClient) {
        this.repo   = repo;
        this.mapper = mapper;
        this.lookup = lookup;
        this.keycloakService = keycloakService;
        this.schoolClient = schoolClient;
    }

    @Override
    public List<StudentDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public StudentDto findByKeycloakUserId(UUID keycloakUserId) {
        return mapper.toDto(getEntityByKeycloakUserId(keycloakUserId));
    }

    @Override
    @Transactional
    public StudentDto create(CreateStudentDto dto) {
        if (dto.schoolClassId() != null) {
            schoolClient.getSchoolClassById(dto.schoolClassId());
        }
        keycloakService.assignRealmRole(dto.keycloakUserId(), "ROLE_STUDENT");
        Student entity = mapper.toEntity(dto);
        repo.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public StudentDto update(UUID kcId, UpdateStudentDto dto) {
        if (dto.schoolClassId() != null) {
            schoolClient.getSchoolClassById(dto.schoolClassId());
        }
        Student entity = repo.findByKeycloakUserId(kcId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + kcId));
        mapper.updateEntityFromDto(dto, entity);
        repo.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public void delete(UUID keycloakUserId) {
        Student entity = getEntityByKeycloakUserId(keycloakUserId);
        repo.delete(entity);
    }

    @Override
    public Student getEntityByKeycloakUserId(UUID keycloakUserId) {
        return repo.findByKeycloakUserId(keycloakUserId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found: " + keycloakUserId));
    }

    @Override
    @Transactional
    public StudentDto updateParents(UUID studentKcId, List<UUID> parentKcIds) {
        Student student = lookup.getStudentEntity(studentKcId);
        if (parentKcIds != null) {
            Set<Parent> parents = parentKcIds.stream()
                    .map(lookup::getParentEntity)
                    .collect(Collectors.toSet());
            student.setParents(parents);
        }
        repo.save(student);
        return mapper.toDto(student);
    }

    @Override
    public List<StudentDto> findAllBySchoolClassId(Long classId) {
        schoolClient.getSchoolClassById(classId);
        return repo.findAllBySchoolClassId(classId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void assignStudentToClass(UUID studentKcId, Long classId) {
        schoolClient.getSchoolClassById(classId);
        Student student = getEntityByKeycloakUserId(studentKcId);
        student.setSchoolClassId(classId);
        repo.save(student);
    }

    @Override
    public void unassignStudentFromClass(UUID studentKcId, Long classId) {
        if (classId != null) {
            schoolClient.getSchoolClassById(classId);
        }
        Student student = getEntityByKeycloakUserId(studentKcId);
        if (classId == null || classId.equals(student.getSchoolClassId())) {
            student.setSchoolClassId(null);
            repo.save(student);
        }
    }

    @Override
    public void clearClassForStudents(List<UUID> studentKcIds) {
        if (studentKcIds == null) return;
        for (UUID id : studentKcIds) {
            Student s = getEntityByKeycloakUserId(id);
            s.setSchoolClassId(null);
            repo.save(s);
        }
    }

    @Override
    public void clearClassReference(Long classId) {
        schoolClient.getSchoolClassById(classId);
        List<Student> students = repo.findAllBySchoolClassId(classId);
        for (Student s : students) {
            s.setSchoolClassId(null);
        }
        repo.saveAll(students);
    }

}