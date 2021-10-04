package edu.anna.exchangepermissions;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

class ViewPermissionServiceTest {

    private static final String ACCOUNT_ID = "123";
    private static final String EXCHANGE_ID = "LSE";

    private final ViewPermissionRepository repository = mock(ViewPermissionRepository.class);
    private final ViewPermissionService service = new ViewPermissionService(repository);

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
    void shouldChangePermissionCorrectly() {
        UserPermissionId userPermissionId = new UserPermissionId(ACCOUNT_ID, EXCHANGE_ID);
        UserPermission userPermission = new UserPermission(userPermissionId, ViewPermission.L1);

        service.changePermission(EXCHANGE_ID, ACCOUNT_ID, ViewPermission.L1);

        verify(repository).save(userPermission);
    }

}