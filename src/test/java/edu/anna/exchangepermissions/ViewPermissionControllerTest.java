package edu.anna.exchangepermissions;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ViewPermissionControllerTest {

    private final ViewPermissionService viewPermissionService = mock(ViewPermissionService.class);
    private final ViewPermissionController controller = new ViewPermissionController(viewPermissionService);
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    @Test
    void itShouldCallService() throws Exception {


        when(viewPermissionService.getPermission("LSE", "123")).thenReturn(ViewPermission.OFF);

        mockMvc.perform(get("/exchange-permissions/view-permission?exchangeId=LSE&accountId=123"))
                .andExpect(status().isOk())
                .andExpect(content().string("OFF"));
    }
}