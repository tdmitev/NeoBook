package org.neobook.userservice.controller;

import jakarta.validation.Valid;
import org.neobook.userservice.dto.CreateTeacherDto;
import org.neobook.userservice.dto.TeacherDto;
import org.neobook.userservice.dto.UpdateProfileDto;
import org.neobook.userservice.dto.UpdateTeacherDto;
import org.neobook.userservice.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teachers")
@PreAuthorize("hasAnyRole('ADMIN','HEADMASTER')")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherDto> getAllTeachers() {
        return teacherService.findAll();
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable UUID id) {
        return teacherService.findByKeycloakUserId(id);
    }

    @PutMapping("/{id}")
    public TeacherDto updateTeacher(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTeacherDto request) {
        return teacherService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable UUID id) {
        teacherService.delete(id);
    }
}
