package com.tsu.projectX.data.responseDto;

import com.tsu.projectX.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponseDto {

    private UUID id;
    private String nickname;
    private String country;
    private String password;
    private String team;
    private String wantedTeam;
    private String role;
    private String wantedRole;

    public static UserResponseDto fromUser(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getNickname(),
                user.getCountry(),
                user.getPassword(),
                user.getTeam() == null ? null : user.getTeam().getName(),
                user.getWantedTeam(),
                user.getRole().getName(),
                user.getWantedRole()
        );
    }

    public static List<UserResponseDto> fromListUser(List<User> users) {
        List<UserResponseDto> usersResponse = new ArrayList<>();
        users.forEach(user -> usersResponse.add(fromUser(user)));
        return usersResponse;
    }
}
