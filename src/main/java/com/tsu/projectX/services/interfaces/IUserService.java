package com.tsu.projectX.services.interfaces;

import com.tsu.projectX.entities.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    User get(UUID id);

    List<User> getAll();

    boolean create(User user);

    boolean update(UUID id, User user);

    boolean delete(UUID id);
}
