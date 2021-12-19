package com.tsu.projectX.services;

import com.tsu.projectX.data.responseDto.TeamResponseDto;
import com.tsu.projectX.entities.Team;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.ITeamRepository;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.tsu.projectX.config.AuthConfig.ROLE_COACH;
import static com.tsu.projectX.config.AuthConfig.ROLE_MANAGER;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private ITeamRepository teamRepository;
    @Autowired
    private IUserRepository userRepository;

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
    public TeamResponseDto addCoach(UUID teamId, UUID userId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalTeam.isEmpty() || optionalUser.isEmpty()) {
            return null;
        }

        Team team = optionalTeam.get();
        User user = optionalUser.get();
        if (!ROLE_COACH.equals(user.getRole().getName())) {
            return null;
        }
        team.setCoach(user);
        teamRepository.save(team);
        return TeamResponseDto.fromTeam(team);
    }

    @Override
    public TeamResponseDto addManager(UUID teamId, UUID userId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalTeam.isEmpty() || optionalUser.isEmpty()) {
            return null;
        }

        Team team = optionalTeam.get();
        User user = optionalUser.get();
        if (!ROLE_MANAGER.equals(user.getRole().getName())) {
            return null;
        }
        team.setManager(user);
        teamRepository.save(team);
        return TeamResponseDto.fromTeam(team);
    }

    @Override
    public Team addPlayer(UUID id) {
        return null;
    }
}
