package org.neobook.userservice.controller;

import jakarta.validation.Valid;
import org.neobook.userservice.dto.CreateParentDto;
import org.neobook.userservice.dto.ParentDto;
import org.neobook.userservice.dto.UpdateParentDto;
import org.neobook.userservice.service.ParentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parents")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PARENT','HEADMASTER','ADMIN')")
    public List<ParentDto> getAllParents() {
        return parentService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PARENT','HEADMASTER','ADMIN')")
    public ParentDto getParent(@PathVariable("id") UUID id) {
        return parentService.findByKeycloakUserId(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PARENT','HEADMASTER','ADMIN')")
    public ParentDto createParent(@Valid @RequestBody CreateParentDto request) {
        return parentService.create(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PARENT','HEADMASTER','ADMIN')")
    public ParentDto updateParent(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateParentDto request) {
        return parentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PARENT','HEADMASTER','ADMIN')")
    public void deleteParent(@PathVariable("id") UUID id) {
        parentService.delete(id);
    }
}

