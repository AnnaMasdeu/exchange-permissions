package edu.anna.exchangepermissions;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class UserPermission {

    @EmbeddedId
    private UserPermissionId userPermissionId;
    private ViewPermission viewPermission;

    public UserPermission(UserPermissionId userPermissionId, ViewPermission viewPermission) {
        this.userPermissionId = userPermissionId;
        this.viewPermission = viewPermission;
    }

    private UserPermission() {

    }

    public UserPermissionId getUserPermissionId() {
        return userPermissionId;
    }

    public ViewPermission getViewPermission() {
        return viewPermission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermission that = (UserPermission) o;
        return Objects.equals(userPermissionId, that.userPermissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPermissionId);
    }
}
