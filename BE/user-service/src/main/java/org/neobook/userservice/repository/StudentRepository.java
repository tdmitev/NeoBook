package org.neobook.userservice.repository;

import org.neobook.userservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByKeycloakUserId(UUID keycloakUserId);
    boolean existsByKeycloakUserId(UUID keycloakUserId);
    List<Student> findAllByKeycloakUserIdIn(List<UUID> keycloakUserIds);
    List<Student> findAllBySchoolClassId(Long schoolClassId);
}
