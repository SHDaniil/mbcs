package com.tsu.projectX.services;

import com.tsu.projectX.data.UserLogin;
import com.tsu.projectX.data.UserRegiter;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements IAuthService {

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
    //TODO Refactor
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
