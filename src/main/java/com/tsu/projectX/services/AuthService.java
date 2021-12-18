package com.tsu.projectX.services;

import com.tsu.projectX.data.responseDto.AuthResponse;
import com.tsu.projectX.data.UserLogin;
import com.tsu.projectX.data.UserRegiter;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IRoleRepository;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tsu.projectX.config.AuthConfig.ROLE_USER;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public boolean checkAuthToken(UUID authToken) {
        return userRepository.findByAuthToken(authToken) != null;
    }

    @Override
    public AuthResponse login(UserLogin userLogin) {
        User userFromDB = userRepository.findByNickname(userLogin.getNickname());
        if (userFromDB == null) {
            return null;
        }
        return userFromDB.getPassword().equals(userLogin.getPassword())
                ? AuthResponse.fromUser(userFromDB)
                : null;
    }

    @Override
    public AuthResponse registerNewUserAccount(UserRegiter userRegiter) {
        User userFromDb = userRepository.findByNickname(userRegiter.getNickname());
        if (userFromDb != null) {
            return null;
        }
        User user = userRegiter.toUser();
        user.setRole(roleRepository.findByName(ROLE_USER));
        user.setAuthToken(UUID.randomUUID());
        userRepository.save(user);
        return AuthResponse.fromUser(user);
    }
}
