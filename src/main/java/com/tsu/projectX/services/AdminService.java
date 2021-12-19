package com.tsu.projectX.services;

import com.tsu.projectX.entities.Role;
import com.tsu.projectX.entities.Team;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IRoleRepository;
import com.tsu.projectX.repositories.ITeamRepository;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.tsu.projectX.config.AuthConfig.*;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ITeamRepository teamRepository;

    @Override
    public boolean approveUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        Role wantedRole = roleRepository.findByName(user.getWantedRole());
        if (wantedRole != null) {
            user.setRole(wantedRole);
            user.setWantedRole(null);
        }

        Team wantedTeam = teamRepository.findByName(user.getWantedTeam());
        if (wantedTeam != null) {
            if (ROLE_COACH.equals(user.getRole().getName())) {
                user.setCouchingTeam(wantedTeam);
            } else if (ROLE_MANAGER.equals(user.getRole().getName())) {
                user.setManagingTeam(wantedTeam);
            } else if (ROLE_PLAYER.equals(user.getRole().getName())) {
                user.setTeam(wantedTeam);
            }
            user.setWantedTeam(null);
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean rejectUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        user.setWantedRole(null);
        user.setWantedTeam(null);
        userRepository.save(user);
        return true;
    }
}
