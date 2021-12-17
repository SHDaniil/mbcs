package com.tsu.projectX.controllers;

import com.tsu.projectX.data.UserLogin;
import com.tsu.projectX.services.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@CrossOrigin
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private IAuthService authService;

    @PostMapping
    public ResponseEntity<UUID> login(@RequestBody UserLogin userLogin) {
        UUID authToken = authService.login(userLogin);
        return authToken != null
                ? new ResponseEntity<>(authToken, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
