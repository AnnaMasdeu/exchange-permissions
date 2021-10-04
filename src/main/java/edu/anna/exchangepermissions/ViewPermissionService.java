package edu.anna.exchangepermissions;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViewPermissionService {

    private final ViewPermissionRepository viewPermissionRepository;

    public ViewPermissionService(ViewPermissionRepository viewPermissionRepository) {
        this.viewPermissionRepository = viewPermissionRepository;
    }

    public ViewPermission getPermission(String exchangeId, String accountId) {
        UserPermissionId userPermissionId = new UserPermissionId(accountId, exchangeId);

        Optional<UserPermission> viewPermission = viewPermissionRepository.findById(userPermissionId);
        return viewPermission.map(UserPermission::getViewPermission)
                .orElse(ViewPermission.OFF);
    }

    public void changePermission(String exchangeId, String accountId, ViewPermission viewPermission) {
        UserPermissionId userPermissionId = new UserPermissionId(accountId, exchangeId);
        UserPermission userPermission = new UserPermission(userPermissionId, viewPermission);

        viewPermissionRepository.save(userPermission);
    }
}
