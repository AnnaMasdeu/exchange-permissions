package edu.anna.exchangepermissions;

import edu.anna.exchangepermissions.wallet.Charge;
import edu.anna.exchangepermissions.wallet.Wallet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

class ViewPermissionServiceTest {

    private static final String ACCOUNT_ID = "123";
    private static final String EXCHANGE_ID = "LSE";

    private final ViewPermissionRepository repository = mock(ViewPermissionRepository.class);
    private final Wallet wallet = mock(Wallet.class);
    private final ViewPermissionService service = new ViewPermissionService(repository, wallet);

    @Test
    void shouldGetPermissionCorrectly() {
        UserPermissionId permissionId = new UserPermissionId(ACCOUNT_ID, EXCHANGE_ID);
        UserPermission userPermission = new UserPermission(permissionId, ViewPermission.L1);
        when(repository.findById(permissionId)).thenReturn(Optional.of(userPermission));

        ViewPermission actualViewPermission = service.getPermission(EXCHANGE_ID, ACCOUNT_ID);

        verify(repository).findById(permissionId);
        assertThat(actualViewPermission, equalTo(ViewPermission.L1));
    }

    @Test
    void shouldGetPermissionOffByDefault() {
        UserPermissionId permissionId = new UserPermissionId(ACCOUNT_ID, EXCHANGE_ID);
        when(repository.findById(permissionId)).thenReturn(Optional.empty());

        ViewPermission actualViewPermission = service.getPermission(EXCHANGE_ID, ACCOUNT_ID);

        verify(repository).findById(permissionId);
        assertThat(actualViewPermission, equalTo(ViewPermission.OFF));
    }

    @Test
    void shouldChangePermissionCorrectlyToL1() {
        shouldChangePermissionCorrectly(ViewPermission.L1, Charge.permissionCharge(ViewPermission.L1));
    }

    @Test
    void shouldChangePermissionCorrectlyToL2() {
        shouldChangePermissionCorrectly(ViewPermission.L2, Charge.permissionCharge(ViewPermission.L2));
    }

    @Test
    void shouldChangePermissionCorrectlyToOFF() {
        shouldChangePermissionCorrectly(ViewPermission.OFF, Charge.permissionCharge(ViewPermission.OFF));
    }

    @Test
    void shouldChangePermissionCorrectlyFromL1ToL2() {
        UserPermissionId userPermissionId = new UserPermissionId(ACCOUNT_ID, EXCHANGE_ID);
        Charge upgrade = new Charge(new BigDecimal("5"), "EUR", "A concept");

        when(repository.findById(userPermissionId)).thenReturn(Optional.of(new UserPermission(userPermissionId, ViewPermission.L1)));

        shouldChangePermissionCorrectly(ViewPermission.L2, Optional.of(upgrade));
    }

    @Test
    void shouldNotChargeFromL2ToL1() {
        UserPermissionId userPermissionId = new UserPermissionId(ACCOUNT_ID, EXCHANGE_ID);

        when(repository.findById(userPermissionId)).thenReturn(Optional.of(new UserPermission(userPermissionId, ViewPermission.L2)));

        shouldChangePermissionCorrectly(ViewPermission.L1, Optional.empty());
    }

    private void shouldChangePermissionCorrectly(ViewPermission viewPermission, Optional<Charge> expectedCharge) {
        UserPermissionId userPermissionId = new UserPermissionId(ACCOUNT_ID, EXCHANGE_ID);
        UserPermission userPermission = new UserPermission(userPermissionId, viewPermission);

        service.changePermission(EXCHANGE_ID, ACCOUNT_ID, viewPermission);

        expectedCharge.ifPresentOrElse(chargedInWallet(), noChargesHappened());

        verify(repository).save(userPermission);
    }

    private Runnable noChargesHappened() {
        return () -> verifyNoInteractions(wallet);
    }

    private Consumer<Charge> chargedInWallet() {
        return charge -> verify(wallet).charge(charge);
    }

}