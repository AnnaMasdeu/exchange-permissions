package edu.anna.exchangepermissions;

import edu.anna.exchangepermissions.wallet.Charge;
import edu.anna.exchangepermissions.wallet.Wallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

import static edu.anna.exchangepermissions.ViewPermission.OFF;

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
                .orElse(OFF);
    }

    public void changePermission(String exchangeId, String accountId, ViewPermission newViewPermission) {
        UserPermissionId userPermissionId = new UserPermissionId(accountId, exchangeId);

        ViewPermission currentViewPermission = viewPermissionRepository.findById(userPermissionId)
                .map(UserPermission::getViewPermission)
                .orElse(OFF);

        Charge totalViewPermissionValue = getCharge(newViewPermission, currentViewPermission);

        wallet.charge(totalViewPermissionValue);
        UserPermission newUserPermission = new UserPermission(userPermissionId, newViewPermission);
        viewPermissionRepository.save(newUserPermission);
    }

    private Charge getCharge(ViewPermission newViewPermission, ViewPermission currentViewPermission) {
        if (newViewPermission.isHigherPermission(currentViewPermission)) {
            Charge totalViewPermissionValue = Charge.permissionCharge(newViewPermission);
            Charge alreadyPaidCharge = Charge.permissionCharge(currentViewPermission);
            return totalViewPermissionValue.minus(alreadyPaidCharge);
        }
        return new Charge(new BigDecimal("0"), "EUR", "A concept");
    }
}
