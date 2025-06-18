package org.neobook.userservice.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "teacher", schema = "user_service")
public class Teacher extends BaseEntity {

    @Column(name = "keycloak_user_id", nullable = false, unique = true)
    private UUID keycloakUserId;

    @Column(length = 20)
    private String phone;

    @Column(name = "school_id")
    private Long schoolId;

    @ElementCollection
    @CollectionTable(
            name = "teacher_subject_ids",
            schema = "user_service",
            joinColumns = @JoinColumn(name = "teacher_id")
    )
    @Column(name = "subject_id")
    private java.util.Set<Long> subjectIds = new java.util.HashSet<>();

    public UUID getKeycloakUserId() {
        return keycloakUserId;
    }

    public void setKeycloakUserId(UUID keycloakUserId) {
        this.keycloakUserId = keycloakUserId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public java.util.Set<Long> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(java.util.Set<Long> subjectIds) {
        this.subjectIds = subjectIds;
    }
}