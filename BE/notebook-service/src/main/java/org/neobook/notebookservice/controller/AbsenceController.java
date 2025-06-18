package org.neobook.notebookservice.controller;

import org.neobook.notebookservice.dto.AbsenceDto;
import org.neobook.notebookservice.dto.CreateAbsenceDto;
import org.neobook.notebookservice.dto.UpdateAbsenceDto;
import org.neobook.notebookservice.service.AbsenceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {

    private final AbsenceService service;

    public AbsenceController(AbsenceService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN','STUDENT','PARENT')")
    public Flux<AbsenceDto> getAbsences(@RequestParam("studentId") UUID studentId) {
        return service.getAbsencesByStudent(studentId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public Mono<AbsenceDto> create(@RequestBody CreateAbsenceDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public Mono<AbsenceDto> update(@PathVariable String id, @RequestBody UpdateAbsenceDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','HEADMASTER','ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
