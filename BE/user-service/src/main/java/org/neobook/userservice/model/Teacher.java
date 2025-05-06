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
}