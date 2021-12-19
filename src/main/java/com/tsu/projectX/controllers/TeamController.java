package com.tsu.projectX.controllers;

import com.tsu.projectX.data.requestDto.UserTeam;
import com.tsu.projectX.data.responseDto.TeamResponseDto;
import com.tsu.projectX.services.interfaces.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private ITeamService teamService;

    @GetMapping("{id}")
    private ResponseEntity<TeamResponseDto> get(@PathVariable(name = "id") UUID id) {
        TeamResponseDto team = teamService.get(id);
        return team != null
                ? new ResponseEntity<>(team, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    private ResponseEntity<List<TeamResponseDto>> getAll() {
        List<TeamResponseDto> teams = teamService.getAll();
        return teams != null
                ? new ResponseEntity<>(teams, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/addCoach")
    private ResponseEntity<?> addCoach(@RequestBody UserTeam request) {
        boolean added = teamService.addCoach(request.getTeamId(), request.getUserId());
        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/addManager")
    private ResponseEntity<?> addManager(@RequestBody UserTeam request) {
        boolean added = teamService.addManager(request.getTeamId(), request.getUserId());
        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/addPlayer")
    private ResponseEntity<?> addPlayer(@RequestBody UserTeam request) {
        boolean added = teamService.addPlayer(request.getTeamId(), request.getUserId());
        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteCoach")
    private ResponseEntity<?> deleteCoach(@RequestBody UserTeam request) {
        boolean deleted = teamService.deleteCoach(request.getTeamId(), request.getUserId());
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteManager")
    private ResponseEntity<?> deleteManager(@RequestBody UserTeam request) {
        boolean deleted = teamService.deleteManager(request.getTeamId(), request.getUserId());
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deletePlayer")
    private ResponseEntity<?> deletePlayer(@RequestBody UserTeam request) {
        boolean deleted = teamService.deletePlayer(request.getTeamId(), request.getUserId());
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
