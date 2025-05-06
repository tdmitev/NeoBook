package org.neobook.userservice.service;

import org.neobook.userservice.dto.CreateParentDto;
import org.neobook.userservice.dto.ParentDto;
import org.neobook.userservice.dto.UpdateParentDto;
import org.neobook.userservice.model.Parent;

import java.util.List;
import java.util.UUID;

public interface ParentService {
    List<ParentDto> findAll();
    ParentDto findByKeycloakUserId(UUID keycloakUserId);
    ParentDto create(CreateParentDto request);
    ParentDto update(UUID keycloakUserId, UpdateParentDto request);
    void delete(UUID keycloakUserId);
    Parent getEntityByKeycloakUserId(UUID keycloakUserId);
}
