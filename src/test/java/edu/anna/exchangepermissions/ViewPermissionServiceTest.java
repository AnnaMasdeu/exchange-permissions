package edu.anna.exchangepermissions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

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
        shouldChangePermissionCorrectly(ViewPermission.L1);
    }

    @Test
    void shouldChangePermissionCorrectlyToL2() {
        shouldChangePermissionCorrectly(ViewPermission.L2);
    }

    @Test
    void shouldChangePermissionCorrectlyToOFF() {
        shouldChangePermissionCorrectly(ViewPermission.OFF);
    }

    private void shouldChangePermissionCorrectly(ViewPermission viewPermission) {
        UserPermissionId userPermissionId = new UserPermissionId(ACCOUNT_ID, EXCHANGE_ID);
        UserPermission userPermission = new UserPermission(userPermissionId, viewPermission);

        service.changePermission(EXCHANGE_ID, ACCOUNT_ID, viewPermission);

        Charge.permissionCharge(viewPermission)
                .ifPresentOrElse(chargedInWallet(), noChargesHappened());

        verify(repository).save(userPermission);
    }

    private Runnable noChargesHappened() {
        return () -> verifyNoInteractions(wallet);
    }

    private Consumer<Charge> chargedInWallet() {
        return charge -> verify(wallet).charge(charge);
    }

}