package org.neobook.notebookservice.controller;

import org.neobook.notebookservice.dto.CreateGradeDto;
import org.neobook.notebookservice.dto.GradeDto;
import org.neobook.notebookservice.dto.UpdateGradeDto;
import org.neobook.notebookservice.service.GradeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService service;

    public GradeController(GradeService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN','STUDENT','PARENT')")
    public Flux<GradeDto> getGrades(@RequestParam("studentId") UUID studentId) {
        return service.getGradesByStudent(studentId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public Mono<GradeDto> create(@RequestBody CreateGradeDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public Mono<GradeDto> update(@PathVariable String id, @RequestBody UpdateGradeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
