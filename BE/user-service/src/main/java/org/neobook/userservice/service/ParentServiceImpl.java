package org.neobook.userservice.service;

import org.neobook.userservice.dto.UpdateParentDto;
import org.neobook.userservice.exception.ResourceNotFoundException;
import org.neobook.userservice.mapper.ParentMapper;
import org.neobook.userservice.model.Parent;
import org.neobook.userservice.repository.ParentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.neobook.userservice.dto.CreateParentDto;
import org.neobook.userservice.dto.ParentDto;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ParentServiceImpl implements ParentService {
    private final ParentRepository repo;
    private final ParentMapper mapper;
    private final SharedLookupService lookup;
    private final KeycloakService keycloakService;
    private final StudentService studentService;

    public ParentServiceImpl(ParentRepository repo,
                             ParentMapper mapper,
                             SharedLookupService lookup,
                             KeycloakService keycloakService,
                             StudentService studentService) {
        this.repo   = repo;
        this.mapper = mapper;
        this.lookup = lookup;
        this.keycloakService = keycloakService;
        this.studentService = studentService;
    }

    @Override
    public List<ParentDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ParentDto findByKeycloakUserId(UUID keycloakUserId) {
        Parent entity = getEntityByKeycloakUserId(keycloakUserId);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public ParentDto create(CreateParentDto dto) {
        keycloakService.assignRealmRole(dto.keycloakUserId(), "ROLE_PARENT");

        Parent parent = mapper.toEntity(dto);
        repo.save(parent);

        if (dto.studentKeycloakUserIds() != null) {
            dto.studentKeycloakUserIds().forEach(studentKcId ->
                    studentService.updateParents(
                            studentKcId,
                            List.of(parent.getKeycloakUserId())
                    )
            );
        }

        return mapper.toDto(parent);
    }

    @Override
    @Transactional
    public ParentDto update(UUID kcId, UpdateParentDto dto) {
        Parent parent = repo.findByKeycloakUserId(kcId)
                .orElseThrow(() -> new RuntimeException("Parent not found: " + kcId));
        mapper.updateEntityFromDto(dto, parent);
        repo.save(parent);

        if (dto.studentKeycloakUserIds() != null) {
            dto.studentKeycloakUserIds().forEach(studentKcId ->
                    studentService.updateParents(
                            studentKcId,
                            List.of(parent.getKeycloakUserId())
                    )
            );
        }
        return mapper.toDto(parent);
    }

    @Override
    public void delete(UUID keycloakUserId) {
        Parent entity = getEntityByKeycloakUserId(keycloakUserId);
        repo.delete(entity);
    }

    @Override
    public Parent getEntityByKeycloakUserId(UUID keycloakUserId) {
        return repo.findByKeycloakUserId(keycloakUserId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Parent not found: " + keycloakUserId));
    }
}

