package org.neobook.userservice.service;



import org.neobook.userservice.model.Parent;
import org.neobook.userservice.model.Student;

import java.util.UUID;

public interface SharedLookupService {
    Student getStudentEntity(UUID keycloakUserId);
    Parent getParentEntity(UUID keycloakUserId);
}
