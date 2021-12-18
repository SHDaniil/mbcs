package com.tsu.projectX.controllers;

import com.tsu.projectX.services.interfaces.IAdminService;
import com.tsu.projectX.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller()
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PutMapping("/approveUserRole/{id}")
    public ResponseEntity<?> approveUserRole(@PathVariable(name = "id") UUID id) {
        boolean approved = adminService.approveUserRole(id);
        return approved
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
