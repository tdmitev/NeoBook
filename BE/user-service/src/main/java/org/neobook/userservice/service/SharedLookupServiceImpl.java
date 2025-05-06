package org.neobook.userservice.service;


import org.neobook.userservice.exception.ResourceNotFoundException;
import org.neobook.userservice.repository.ParentRepository;
import org.neobook.userservice.repository.StudentRepository;
import org.neobook.userservice.model.Parent;
import org.neobook.userservice.model.Student;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SharedLookupServiceImpl implements SharedLookupService {
    private final StudentRepository studentRepo;
    private final ParentRepository parentRepo;

    public SharedLookupServiceImpl(StudentRepository studentRepo,
                                   ParentRepository parentRepo) {
        this.studentRepo  = studentRepo;
        this.parentRepo   = parentRepo;
    }

    @Override
    public Student getStudentEntity(UUID keycloakUserId) {
        return studentRepo.findByKeycloakUserId(keycloakUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + keycloakUserId));
    }

    @Override
    public Parent getParentEntity(UUID keycloakUserId) {
        return parentRepo.findByKeycloakUserId(keycloakUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found: " + keycloakUserId));
    }
}