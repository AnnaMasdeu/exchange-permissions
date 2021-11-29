package edu.anna.exchangepermissions;

public enum ViewPermission {
    OFF, L1, L2;

    public boolean isHigherPermission(ViewPermission viewPermission) {
        return this.compareTo(viewPermission) > 0;
    }

}
