package com.tsu.projectX.services.interfaces;

import com.tsu.projectX.data.requestDto.UserRequestDto;
import com.tsu.projectX.data.responseDto.UserResponseDto;
import com.tsu.projectX.entities.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    UserResponseDto get(UUID id);

    List<UserResponseDto> getAll();

    boolean create(User user);

    boolean update(UUID id, UserRequestDto userRequestDto);

    boolean delete(UUID id);
}
