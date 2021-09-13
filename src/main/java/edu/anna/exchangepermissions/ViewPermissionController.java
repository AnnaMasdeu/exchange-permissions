package edu.anna.exchangepermissions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class ViewPermissionController {

    private ViewPermissionService viewPermissionService;

    public ViewPermissionController(ViewPermissionService viewPermissionService) {

    }

    @ResponseStatus(value =  HttpStatus.OK)
    @GetMapping("/something")
    public ViewPermission getPermission() {
        return null;
    }
}