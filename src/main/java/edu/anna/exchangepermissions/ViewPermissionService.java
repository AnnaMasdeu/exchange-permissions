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

    public void changePermission(String exchangeId, String accountId, ViewPermission newViewPermission) {
        UserPermissionId userPermissionId = new UserPermissionId(accountId, exchangeId);

        Optional<UserPermission> currentUserPermission = viewPermissionRepository.findById(userPermissionId);

        if (currentUserPermission.isPresent()) {
            ViewPermission currentViewPermission = currentUserPermission.get().getViewPermission();
            Optional<Charge> alreadyPaidCharge = Charge.permissionCharge(currentViewPermission);
            Optional<Charge> totalViewPermissionValue = Charge.permissionCharge(newViewPermission);

            Charge upgradeCharge = totalViewPermissionValue.get().minus(alreadyPaidCharge.get());
            wallet.charge(upgradeCharge);
        } else {
            Charge.permissionCharge(newViewPermission).ifPresent(wallet::charge);
        }

        UserPermission newUserPermission = new UserPermission(userPermissionId, newViewPermission);
        viewPermissionRepository.save(newUserPermission);
    }
}
