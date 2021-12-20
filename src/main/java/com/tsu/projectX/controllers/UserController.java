package com.tsu.projectX.controllers;

import com.tsu.projectX.data.requestDto.UserRequestDto;
import com.tsu.projectX.data.responseDto.UserResponseDto;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.services.interfaces.IAuthService;
import com.tsu.projectX.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.tsu.projectX.config.AuthConfig.*;

@RestController()
@CrossOrigin
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAuthService authService;

    @Deprecated
    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader(name = "auth-token") UUID authToken,
            @RequestBody User user) {
        return userService.create(user)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> get(
            @RequestHeader(name = "auth-token") UUID authToken,
            @PathVariable(name = "id") UUID id) {
        boolean accessible = authService.checkAuthAndPermission(authToken);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserResponseDto user = userService.get(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAll(
            @RequestHeader(name = "auth-token") UUID authToken) {
        boolean accessible = authService.checkAuthAndPermission(authToken);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<UserResponseDto> users = userService.getAll();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @RequestHeader(name = "auth-token") UUID authToken,
            @PathVariable(name = "id") UUID id,
            @RequestBody UserRequestDto userRequestDto) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean updated = userService.update(id, userRequestDto);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(
            @RequestHeader(name = "auth-token") UUID authToken,
            @PathVariable(name = "id") UUID id) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean deleted = userService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
