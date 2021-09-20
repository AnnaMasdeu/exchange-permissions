package edu.anna.exchangepermissions;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ViewPermissionControllerTest {

    private final ViewPermissionService viewPermissionService = mock(ViewPermissionService.class);
    private final ViewPermissionController controller = new ViewPermissionController(viewPermissionService);
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    @Test
    void itShouldDelegateToServiceForGettingPermission() throws Exception {

    when(viewPermissionService.getPermission("LSE", "123")).thenReturn(ViewPermission.OFF);

        mockMvc.perform(get("/exchange-permissions/view-permission?exchangeId=LSE&accountId=123"))
            .andExpect(status().isOk())
            .andExpect(content().string("\"OFF\""));
    }

    @Test
    void itShouldDelegateToServiceForPuttingPermission() throws Exception {
        mockMvc.perform(put("/exchange-permissions/view-permission?exchangeId=LSE&accountId=123&viewPermission=L1"))
                .andExpect(status().isOk());

        verify(viewPermissionService).changePermission("LSE", "123", ViewPermission.L1);
    }
}