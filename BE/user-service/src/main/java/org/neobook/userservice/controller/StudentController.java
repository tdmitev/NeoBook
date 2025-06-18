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
    public List<StudentDto> getStudents(@RequestParam(value = "classId", required = false) Long classId) {
        if (classId != null) {
            return studentService.findAllBySchoolClassId(classId);
        }
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

    @PutMapping("/add-class/{classId}")
    @PreAuthorize("hasAnyRole('HEADMASTER','ADMIN')")
    public void addClass(@PathVariable("classId") Long classId, @RequestBody UUID studentId) {
        studentService.assignStudentToClass(studentId, classId);
    }

    @PutMapping("/remove-class/{classId}")
    @PreAuthorize("hasAnyRole('HEADMASTER','ADMIN')")
    public void removeClass(@PathVariable("classId") Long classId, @RequestBody UUID studentId) {
        studentService.unassignStudentFromClass(studentId, classId);
    }

    @PutMapping("/clear-class/{classId}")
    @PreAuthorize("hasAnyRole('HEADMASTER','ADMIN')")
    public void clearClass(@PathVariable("classId") Long classId) {
        studentService.clearClassReference(classId);
    }

    @PutMapping("/clear-class")
    @PreAuthorize("hasAnyRole('HEADMASTER','ADMIN')")
    public void clearClassForStudents(@RequestBody List<UUID> studentIds) {
        studentService.clearClassForStudents(studentIds);
    }
}

