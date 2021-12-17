package com.tsu.projectX.controllers;

import com.tsu.projectX.data.UserRegiter;
import com.tsu.projectX.services.interfaces.IAuthenticationService;
import com.tsu.projectX.services.interfaces.IUserService;
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
    private IAuthenticationService authenticationService;

    @PostMapping()
    public ResponseEntity<?> registration(@RequestBody UserRegiter userRegiter) {
        if (!userRegiter.getPassword().equals(userRegiter.getPasswordConfirm())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!authenticationService.registerNewUserAccount(userRegiter)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
