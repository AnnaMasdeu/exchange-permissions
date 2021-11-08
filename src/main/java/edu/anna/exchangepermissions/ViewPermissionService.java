package edu.anna.exchangepermissions;

import edu.anna.exchangepermissions.wallet.Charge;
import edu.anna.exchangepermissions.wallet.Wallet;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViewPermissionService {

    private final ViewPermissionRepository viewPermissionRepository;
    private final Wallet wallet;

    public ViewPermissionService(ViewPermissionRepository viewPermissionRepository, Wallet wallet) {
        this.viewPermissionRepository = viewPermissionRepository;
        this.wallet = wallet;
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

        Charge.permissionCharge(viewPermission).ifPresent(wallet::charge);

        viewPermissionRepository.save(userPermission);
    }
}
