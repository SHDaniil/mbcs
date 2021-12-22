package com.tsu.projectX.services;

import com.tsu.projectX.data.requestDto.UserRequestDto;
import com.tsu.projectX.data.responseDto.UserResponseDto;
import com.tsu.projectX.entities.Team;
import com.tsu.projectX.entities.User;
import com.tsu.projectX.repositories.IRoleRepository;
import com.tsu.projectX.repositories.ITeamRepository;
import com.tsu.projectX.repositories.IUserRepository;
import com.tsu.projectX.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ITeamRepository teamRepository;

    @Override
    public User getByAuthToken(UUID authToken) {
        return userRepository.findByAuthToken(authToken);
    }

    @Override
    public UserResponseDto get(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return UserResponseDto.fromUser(user.get());
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return Collections.emptyList();
        }
        return UserResponseDto.fromListUser(users);
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
    public boolean update(UUID id, UserRequestDto userRequestDto) {
        User userFromDb = userRepository.getById(id);
        if (userFromDb.getId() == null) {
            return false;
        }

        userRequestDto.modifyUser(userFromDb);
        Team team = teamRepository.findByName(userRequestDto.getTeam());
        //TODO
        if (team != null) {
            userFromDb.setTeam(team);
        }

        userFromDb.setRole(roleRepository.findByName(userRequestDto.getRole()));
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
