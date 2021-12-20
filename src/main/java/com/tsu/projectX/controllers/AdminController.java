package com.tsu.projectX.controllers;

import com.tsu.projectX.services.interfaces.IAdminService;
import com.tsu.projectX.services.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.tsu.projectX.config.AuthConfig.ROLE_ADMIN;

@Controller()
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IAuthService authService;

    @PutMapping("/approveUser/{id}")
    public ResponseEntity<?> approveUser(
            @RequestHeader(name = "auth-token") UUID authToken,
            @PathVariable(name = "id") UUID id) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean approved = adminService.approveUser(id);
        return approved
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/rejectUser/{id}")
    public ResponseEntity<?> rejectUser(
            @RequestHeader(name = "auth-token") UUID authToken,
            @PathVariable(name = "id") UUID id) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean rejected = adminService.rejectUser(id);
        return rejected
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
