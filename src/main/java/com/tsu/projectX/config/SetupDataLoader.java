package com.tsu.projectX.config;

import com.tsu.projectX.entities.Role;
import com.tsu.projectX.entities.Team;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IRoleRepository;
import com.tsu.projectX.repositories.ITeamRepository;
import com.tsu.projectX.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.tsu.projectX.config.AuthConfig.*;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ITeamRepository teamRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (String nameRole: ROLES) {
            createRoleIfNotFound(nameRole);
        }

        //ADMIN
        createUserIfNotFound("admin", null, "Admin", null, ROLE_ADMIN);

        //NAVI
        createTeamIfNotFound("Natus Vincere", "Russia", "#1", "232", "23.2");
        createUserIfNotFound("s1mple", "uk", "qwerty", "Natus Vincere", ROLE_PLAYER);
        createUserIfNotFound("Boombl4", "ru", "qwerty", "Natus Vincere", ROLE_PLAYER);
        createUserIfNotFound("electronic", "ru", "qwerty", "Natus Vincere", ROLE_PLAYER);
        createUserIfNotFound("Perfecto", "ru", "qwerty", "Natus Vincere", ROLE_PLAYER);
        createUserIfNotFound("B1t", "uk", "qwerty", "Natus Vincere", ROLE_PLAYER);
        createUserIfNotFound("B1ad3", "uk", "qwerty", null, ROLE_COACH);
        createUserIfNotFound("Shpuntik", "uk", "qwerty", null, ROLE_MANAGER);
        Team natusVincere = teamRepository.findByName("Natus Vincere");
        User user = userRepository.findByNickname("B1ad3");
        User user1 = userRepository.findByNickname("Shpuntik");
        user.setCouchingTeam(natusVincere);
        user1.setManagingTeam(natusVincere);
        userRepository.save(user);
        userRepository.save(user1);

        //Virtus.pro
        createTeamIfNotFound("Virtus.pro", "Russia", "#2", "111", "23.2");
    }

    @Transactional
    void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
    }

    @Transactional
    void createUserIfNotFound(String nickname, String country, String password, String team, String role) {
        User user = userRepository.findByNickname(nickname);
        if (user == null) {
            user = new User();
            user.setNickname(nickname);
            user.setCountry(country);
            user.setPassword(password);
            user.setTeam(teamRepository.findByName(team));
            user.setRole(roleRepository.findByName(role));
            user.setAuthToken(UUID.randomUUID());
            userRepository.save(user);
        }
    }

    void createTeamIfNotFound(String name, String country, String ranking, String topTime, String averageAge) {
        Team team = teamRepository.findByName(name);
        if (team == null) {
            team = new Team();
            team.setName(name);
            team.setCountry(country);
            team.setRanking(ranking);
            team.setTopTime(topTime);
            team.setAverageAge(averageAge);
            teamRepository.save(team);
        }
    }
}
