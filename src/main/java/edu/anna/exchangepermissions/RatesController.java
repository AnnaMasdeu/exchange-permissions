package edu.anna.exchangepermissions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange-permissions")
public class RatesController {

    private ViewPermissionService viewPermissionService;

    public RatesController(ViewPermissionService viewPermissionService) {
        this.viewPermissionService = viewPermissionService;
    }

    @ResponseStatus(value =  HttpStatus.OK)
    @GetMapping("/view-permission")
    public ViewPermission getPermission(@RequestParam String exchangeId,
                                        @RequestParam String accountId) {

        return viewPermissionService.getPermission(exchangeId, accountId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/view-permission")
    public void changePermission(@RequestParam String exchangeId,
                                 @RequestParam String accountId,
                                 @RequestParam ViewPermission viewPermission) {

        viewPermissionService.changePermission(exchangeId, accountId, viewPermission);
    }
}