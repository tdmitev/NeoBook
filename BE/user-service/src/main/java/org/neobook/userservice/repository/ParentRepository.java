package org.neobook.userservice.repository;

import org.neobook.userservice.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, Integer> {
    Optional<Parent> findByKeycloakUserId(UUID keycloakUserId);
    boolean existsByKeycloakUserId(UUID keycloakUserId);
    List<Parent> findAllByKeycloakUserIdIn(List<UUID> keycloakUserIds);
}