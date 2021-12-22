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

import static com.tsu.projectX.config.AuthConfig.*;

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
    public boolean addCoach(UUID teamId, UUID userId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalTeam.isEmpty() || optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        if (!ROLE_COACH.equals(user.getRole().getName())
                || user.getCouchingTeam() != null) {
            return false;
        }
        user.setCouchingTeam(optionalTeam.get());
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean addManager(UUID teamId, UUID userId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalTeam.isEmpty() || optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        if (!ROLE_MANAGER.equals(user.getRole().getName())) {
            return false;
        }
        user.setManagingTeam(optionalTeam.get());
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean addPlayer(UUID teamId, UUID userId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalTeam.isEmpty() || optionalUser.isEmpty()) {
            return false;
        }

        Team team = optionalTeam.get();
        User user = optionalUser.get();
        if (team.getPlayers().size() > 5
                || user.getTeam() != null
                || !ROLE_PLAYER.equals(user.getRole().getName())) {
            return false;
        }
        user.setTeam(team);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteCoach(UUID teamId, UUID userId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalTeam.isEmpty() || optionalUser.isEmpty()) {
            return false;
        }

        Team team = optionalTeam.get();
        User user = optionalUser.get();
        if (!user.getCouchingTeam().getName().equals(team.getName())) {
            return false;
        }
        user.setCouchingTeam(null);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteManager(UUID teamId, UUID userId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalTeam.isEmpty() || optionalUser.isEmpty()) {
            return false;
        }

        Team team = optionalTeam.get();
        User user = optionalUser.get();
        if (!user.getManagingTeam().getName().equals(team.getName())) {
            return false;
        }
        user.setManagingTeam(null);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deletePlayer(UUID teamId, UUID userId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalTeam.isEmpty() || optionalUser.isEmpty()) {
            return false;
        }

        Team team = optionalTeam.get();
        User user = optionalUser.get();
        if (!user.getTeam().getName().equals(team.getName())) {
            return false;
        }
        user.setTeam(null);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean compare(Team team, UUID teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isEmpty()) {
            return false;
        }
        return team.equals(optionalTeam.get());
    }
}
