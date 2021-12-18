package com.tsu.projectX.controllers;

import com.tsu.projectX.data.UserRegiter;
import com.tsu.projectX.data.responseDto.AuthResponse;
import com.tsu.projectX.services.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@CrossOrigin
@RequestMapping(path = "/registration")
public class RegistrationController {

    @Autowired
    private IAuthService authService;

    @PostMapping()
    public ResponseEntity<AuthResponse> registration(@RequestBody UserRegiter userRegiter) {
        if (!userRegiter.getPassword().equals(userRegiter.getPasswordConfirm())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AuthResponse authResponse = authService.registerNewUserAccount(userRegiter);
        return authResponse != null
                ? new ResponseEntity<>(authResponse,HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
