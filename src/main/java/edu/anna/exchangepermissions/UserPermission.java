package edu.anna.exchangepermissions;

public class UserPermission {

    private UserPermissionId userPermissionId;
    private ViewPermission viewPermission;

    public UserPermission(UserPermissionId userPermissionId, ViewPermission viewPermission) {
        this.userPermissionId = userPermissionId;
        this.viewPermission = viewPermission;
    }

    public UserPermissionId getUserPermissionId() {
        return userPermissionId;
    }

    public ViewPermission getViewPermission() {
        return viewPermission;
    }
}
