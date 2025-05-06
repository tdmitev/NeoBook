package org.neobook.userservice.repository;

import org.neobook.userservice.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByKeycloakUserId(UUID keycloakUserId);
    boolean existsByKeycloakUserId(UUID keycloakUserId);
}