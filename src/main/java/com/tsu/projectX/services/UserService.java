package com.tsu.projectX.services;

import com.tsu.projectX.data.UserData;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User get(UUID id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean create(User user) {
        User userFromDb = userRepository.findByNickname(user.getNickname());
        if (userFromDb == null) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean update(UUID id, UserData userData) {
        User userFromDb = userRepository.getById(id);
        if (userFromDb.getId() == null) {
            return false;
        }

        userFromDb.setNickname(userData.getNickname());
        userFromDb.setName(userData.getName());
        userFromDb.setLastName(userData.getLastName());
        userFromDb.setEmail(userData.getEmail());
        userFromDb.setTeam(userData.getTeam());
        userRepository.save(userFromDb);
        return true;
    }

    @Override
    public boolean delete(UUID id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
