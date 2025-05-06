package org.neobook.userservice.controller;


import jakarta.validation.Valid;
import org.neobook.userservice.dto.TeacherDto;
import org.neobook.userservice.dto.UpdateTeacherDto;
import org.neobook.userservice.service.TeacherService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//@RestController
//@RequestMapping("/api/profile")
//public class ProfileController {
//    private final TeacherService teacherService;
//
//    public ProfileController(TeacherService teacherService) {
//        this.teacherService = teacherService;
//    }
//
//    /** Ensure minimal teacher record exists for logged-in user. */
//    @PostMapping("/api/profile/ensure")
//    public void ensureProfile(@AuthenticationPrincipal Jwt jwt) {
//        UUID kcId      = UUID.fromString(jwt.getSubject());
//        String fn      = jwt.getClaim("given_name");
//        String ln      = jwt.getClaim("family_name");
//        String email   = jwt.getClaim("email");
//        teacherService.ensureExists(kcId, "");
//    }
//
//    /** Get profile data for logged-in teacher. */
//    @GetMapping
//    public TeacherDto myTeacherProfile(@AuthenticationPrincipal Jwt jwt) {
//        UUID kcId = UUID.fromString(jwt.getSubject());
//        return teacherService.findByKeycloakUserId(kcId);
//    }
//
//    /** Update profile of logged-in teacher. */
//    @PutMapping
//    public TeacherDto updateProfile(
//            @AuthenticationPrincipal Jwt jwt,
//            @Valid @RequestBody UpdateTeacherDto req
//    ) {
//        UUID kcId = UUID.fromString(jwt.getSubject());
//        return teacherService.update(kcId, req);
//    }
//}