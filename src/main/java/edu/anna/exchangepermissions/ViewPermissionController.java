package edu.anna.exchangepermissions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange-permissions")
public class ViewPermissionController {

    private ViewPermissionService viewPermissionService;

    public ViewPermissionController(ViewPermissionService viewPermissionService) {
        this.viewPermissionService = viewPermissionService;
    }

    @ResponseStatus(value =  HttpStatus.OK)
    @GetMapping("/view-permission")
    public ViewPermission getPermission(@RequestParam String exchangeId,
                                        @RequestParam String accountId) {

        return viewPermissionService.getPermission(exchangeId, accountId);
    }
}