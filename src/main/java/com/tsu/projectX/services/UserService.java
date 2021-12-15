package com.tsu.projectX.services;

import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User get(UUID id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean create(User user) {
        userRepository.saveAndFlush(user);

        //TODO
        return true;
    }

    @Override
    public boolean update(UUID id, User user) {
        User userFromDb = get(id);

        userFromDb.setNickname(user.getNickname());
        userFromDb.setName(user.getName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setTeam(user.getTeam());
        userRepository.saveAndFlush(userFromDb);

        //TODO
        return true;
    }

    @Override
    public boolean delete(UUID id) {
        userRepository.deleteById(id);

        //TODO
        return true;
    }
}
