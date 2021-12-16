package com.tsu.projectX.controllers;

import com.tsu.projectX.data.UserLogin;
import com.tsu.projectX.services.interfaces.IAuthenticationService;
import com.tsu.projectX.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<UUID> login(@RequestBody UserLogin userLogin) {
        UUID authToken = authenticationService.login(userLogin);
        return authToken != null
                ? new ResponseEntity<>(authToken, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
