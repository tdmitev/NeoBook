package org.neobook.userservice.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "students", schema = "user_service")
public class Student extends BaseEntity {

    @Column(name = "keycloak_user_id", nullable = false, unique = true)
    private UUID keycloakUserId;

    @Column(length = 20)
    private String phone;

    @Column(name = "school_class_id")
    private Long schoolClassId;


    @ManyToMany
    @JoinTable(
            name = "student_parents",
            schema = "user_service",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    private Set<Parent> parents = new HashSet<>();

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

    public Set<Parent> getParents() {
        return parents;
    }

    public void setParents(Set<Parent> parents) {
        this.parents = parents;
    }

    public Long getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(Long schoolClassId) {
        this.schoolClassId = schoolClassId;
    }
}