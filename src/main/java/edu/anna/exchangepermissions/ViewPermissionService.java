package edu.anna.exchangepermissions;

import org.springframework.stereotype.Component;

@Component
public class ViewPermissionService {

    public ViewPermission getPermission(String exchangeId, String accountId) {
        return ViewPermission.OFF;
    }
}
