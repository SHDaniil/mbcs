package com.tsu.projectX.controllers;

import com.tsu.projectX.data.requestDto.UserTeam;
import com.tsu.projectX.data.responseDto.TeamResponseDto;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.services.interfaces.IAuthService;
import com.tsu.projectX.services.interfaces.ITeamService;
import com.tsu.projectX.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.tsu.projectX.config.AuthConfig.*;

@RestController
@CrossOrigin
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IAuthService authService;

    @GetMapping("{id}")
    private ResponseEntity<TeamResponseDto> get(
            @RequestHeader(name = "auth-token") UUID authToken,
            @PathVariable(name = "id") UUID id) {
        boolean accessible = authService.checkAuthAndPermission(authToken);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        TeamResponseDto team = teamService.get(id);
        return team != null
                ? new ResponseEntity<>(team, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    private ResponseEntity<List<TeamResponseDto>> getAll(
            @RequestHeader(name = "auth-token") UUID authToken) {
        boolean accessible = authService.checkAuthAndPermission(authToken);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<TeamResponseDto> teams = teamService.getAll();
        return teams != null
                ? new ResponseEntity<>(teams, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/addCoach")
    private ResponseEntity<?> addCoach(
            @RequestHeader(name = "auth-token") UUID authToken,
            @RequestBody UserTeam request) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN, ROLE_MANAGER);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userService.getByAuthToken(authToken);
        if (!teamService.compare(user.getManagingTeam(), request.getTeamId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean added = teamService.addCoach(request.getTeamId(), request.getUserId());
        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/addManager")
    private ResponseEntity<?> addManager(
            @RequestHeader(name = "auth-token") UUID authToken,
            @RequestBody UserTeam request) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean added = teamService.addManager(request.getTeamId(), request.getUserId());
        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/addPlayer")
    private ResponseEntity<?> addPlayer(
            @RequestHeader(name = "auth-token") UUID authToken,
            @RequestBody UserTeam request) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN, ROLE_MANAGER, ROLE_COACH);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userService.getByAuthToken(authToken);
        if (!teamService.compare(user.getCouchingTeam(), request.getTeamId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean added = teamService.addPlayer(request.getTeamId(), request.getUserId());
        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteCoach")
    private ResponseEntity<?> deleteCoach(
            @RequestHeader(name = "auth-token") UUID authToken,
            @RequestBody UserTeam request) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN, ROLE_MANAGER);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userService.getByAuthToken(authToken);
        if (!teamService.compare(user.getManagingTeam(), request.getTeamId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean deleted = teamService.deleteCoach(request.getTeamId(), request.getUserId());
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteManager")
    private ResponseEntity<?> deleteManager(
            @RequestHeader(name = "auth-token") UUID authToken,
            @RequestBody UserTeam request) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean deleted = teamService.deleteManager(request.getTeamId(), request.getUserId());
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deletePlayer")
    private ResponseEntity<?> deletePlayer(
            @RequestHeader(name = "auth-token") UUID authToken,
            @RequestBody UserTeam request) {
        boolean accessible = authService.checkAuthAndPermission(authToken, ROLE_ADMIN, ROLE_MANAGER, ROLE_COACH);
        if (!accessible) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userService.getByAuthToken(authToken);
        if (!teamService.compare(user.getCouchingTeam(), request.getTeamId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean deleted = teamService.deletePlayer(request.getTeamId(), request.getUserId());
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
