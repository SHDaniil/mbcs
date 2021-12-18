package com.tsu.projectX.services;

import com.tsu.projectX.data.responseDto.TeamResponseDto;
import com.tsu.projectX.entities.Team;
import com.tsu.projectX.repositories.ITeamRepository;
import com.tsu.projectX.services.interfaces.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private ITeamRepository teamRepository;

    @Override
    public TeamResponseDto get(UUID id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isEmpty()) {
            return null;
        }
        return TeamResponseDto.fromTeam(team.get());
    }

    @Override
    public List<TeamResponseDto> getAll() {
        List<Team> teams = teamRepository.findAll();
        if (teams.isEmpty()) {
            return Collections.emptyList();
        }
        return TeamResponseDto.fromListTeam(teams);
    }

    @Override
    public Team addPlayer(UUID id) {
        return null;
    }
}
