package com.tsu.projectX.config;

import com.tsu.projectX.entities.Role;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IRoleRepository;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (String nameRole: ROLES) {
            createRoleIfNotFound(nameRole);
        }

        //ADMIN
        createUserIfNotFound("admin", null, "Admin", null, ROLE_ADMIN);

        //NAVI
        createUserIfNotFound("s1mple", "ru", "qwerty", null, ROLE_PLAYER);
        createUserIfNotFound("Boombl4", "ru", "qwerty", null, ROLE_PLAYER);
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
            user.setTeam(team);
            user.setRole(roleRepository.findByName(role));
            user.setAuthToken(UUID.randomUUID());
            userRepository.save(user);
        }
    }
}
