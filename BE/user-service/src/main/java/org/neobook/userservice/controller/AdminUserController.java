package org.neobook.userservice.controller;

import org.neobook.userservice.service.KeycloakService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final KeycloakService roleSvc;

    public AdminUserController(KeycloakService roleSvc) {
        this.roleSvc = roleSvc;
    }

    @PostMapping("/{kcId}/roles/{role}")
    public void grantRole(@PathVariable UUID kcId, @PathVariable String role) {
        roleSvc.assignRealmRole(kcId, role);
    }
}