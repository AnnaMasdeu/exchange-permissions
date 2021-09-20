package edu.anna.exchangepermissions;

import org.springframework.stereotype.Component;

@Component
public class ViewPermissionService {

    private ViewPermissionRepository viewPermissionRepository;

    public ViewPermission getPermission(String exchangeId, String accountId) {
        return ViewPermission.OFF;
    }

    public void changePermission(String exchangeId, String accountId, ViewPermission viewPermission) {

    }
}
