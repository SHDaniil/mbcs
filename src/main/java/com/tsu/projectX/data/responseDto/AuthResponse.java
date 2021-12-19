package com.tsu.projectX.data.responseDto;

import com.tsu.projectX.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthResponse {

    private UUID id;
    private String role;
    private String team;
    private UUID authToken;

    public static AuthResponse fromUser(User user) {
        UserResponseDto userResponseDto = UserResponseDto.fromUser(user);
        return new AuthResponse(
                userResponseDto.getId(),
                userResponseDto.getRole(),
                userResponseDto.getTeam(),
                user.getAuthToken());
    }
}
