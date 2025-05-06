package org.neobook.userservice.controller;

import jakarta.validation.Valid;
import org.neobook.userservice.dto.CreateParentDto;
import org.neobook.userservice.dto.CreateStudentDto;
import org.neobook.userservice.dto.CreateTeacherDto;
import org.neobook.userservice.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyRole('HEADMASTER','ADMIN')")
@RequestMapping("/api/register")
public class RegistrationController {
    private final KeycloakService keycloakService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ParentService parentService;

    public RegistrationController(KeycloakService keycloakService,
                                  TeacherService teacherService,
                                  StudentService studentService,
                                  ParentService parentService) {
        this.keycloakService = keycloakService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.parentService = parentService;
    }

    @PostMapping("/teacher")
    public ResponseEntity<Void> registerTeacher(
            @Valid @RequestBody CreateTeacherDto req) {
        keycloakService.assignRealmRole(req.keycloakUserId(), "ROLE_TEACHER");
        teacherService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/headmaster")
    public ResponseEntity<Void> registerHeadmaster(
            @Valid @RequestBody CreateTeacherDto req) {
        keycloakService.assignRealmRole(req.keycloakUserId(), "ROLE_HEADMASTER");
        teacherService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/student")
    public ResponseEntity<Void> registerStudent(
            @Valid @RequestBody CreateStudentDto req) {
        keycloakService.assignRealmRole(req.keycloakUserId(), "ROLE_STUDENT");
        studentService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/parent")
    public ResponseEntity<Void> registerParent(
            @Valid @RequestBody CreateParentDto req) {
        keycloakService.assignRealmRole(req.keycloakUserId(), "ROLE_PARENT");
        parentService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}