package com.tsu.projectX.services;

import com.tsu.projectX.data.UserLogin;
import com.tsu.projectX.data.UserRegiter;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthenticationService implements IAuthenticationService {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_PLAYER = "ROLE_PLAYER";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_COACH = "ROLE_COACH";

    public static final List<String> ROLES = new ArrayList<>();
    static {
        ROLES.add(ROLE_ADMIN);
        ROLES.add(ROLE_USER);
        ROLES.add(ROLE_PLAYER);
        ROLES.add(ROLE_MANAGER);
        ROLES.add(ROLE_COACH);
    }

    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean checkAuthToken(UUID authToken) {
        return userRepository.findByAuthToken(authToken) != null;
    }

    @Override
    public UUID login(UserLogin userLogin) {
        User userFromDB = userRepository.findByNickname(userLogin.getNickname());
        if (userFromDB == null) {
            return null;
        }

        return userFromDB.getPassword().equals(userLogin.getPassword())
                ? userFromDB.getAuthToken()
                : null;
    }

    @Override
    public boolean registerNewUserAccount(UserRegiter userRegiter) {
        User userFromDB = userRepository.findByNickname(userRegiter.getNickname());
        if (userFromDB != null) {
            return false;
        }

        User user = new User();
        user.setNickname(userRegiter.getNickname());
        user.setName(userRegiter.getName());
        user.setLastName(userRegiter.getLastName());
        user.setPassword(userRegiter.getPassword());
        user.setEmail(userRegiter.getEmail());
        user.setAuthToken(UUID.randomUUID());
        userRepository.saveAndFlush(user);
        return true;
    }
}
