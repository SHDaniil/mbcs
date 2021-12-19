package com.tsu.projectX.controllers;

import com.tsu.projectX.data.requestDto.AddUserTeam;
import com.tsu.projectX.data.responseDto.TeamResponseDto;
import com.tsu.projectX.services.interfaces.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
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
    private ResponseEntity<TeamResponseDto> addCoach(@RequestBody AddUserTeam request) {
        TeamResponseDto team = teamService.addCoach(request.getTeamId(), request.getUserId());
        return team != null
                ? new ResponseEntity<>(team, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/addManager")
    private ResponseEntity<TeamResponseDto> addManager(@RequestBody AddUserTeam request) {
        TeamResponseDto team = teamService.addManager(request.getTeamId(), request.getUserId());
        return team != null
                ? new ResponseEntity<>(team, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
