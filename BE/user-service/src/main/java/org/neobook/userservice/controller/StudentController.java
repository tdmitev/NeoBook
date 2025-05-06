package org.neobook.userservice.controller;

import jakarta.validation.Valid;
import org.neobook.userservice.dto.CreateStudentDto;
import org.neobook.userservice.dto.StudentDto;
import org.neobook.userservice.dto.UpdateStudentDto;
import org.neobook.userservice.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public List<StudentDto> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public StudentDto getStudent(@PathVariable("id") UUID id) {
        return studentService.findByKeycloakUserId(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public StudentDto createStudent(@Valid @RequestBody CreateStudentDto request) {
        return studentService.create(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public StudentDto updateStudent(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateStudentDto request) {
        return studentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public void deleteStudent(@PathVariable("id") UUID id) {
        studentService.delete(id);
    }
}

