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

import static com.tsu.projectX.services.AuthenticationService.ROLES;
import static com.tsu.projectX.services.AuthenticationService.ROLE_PLAYER;

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

        userRepository.save(new User(
                "s1mple",
                "Aleksandr",
                "Kostyliev",
                "s1mple@connectivegames.ru",
                "qwerty",
                null,
                UUID.randomUUID(),
//                roleRepository.findByName(ROLE_PLAYER))); //TODO Fix
                ROLE_PLAYER));
    }

    @Transactional
    void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
    }
}
