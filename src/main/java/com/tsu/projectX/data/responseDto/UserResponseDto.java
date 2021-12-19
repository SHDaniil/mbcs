package com.tsu.projectX.data.responseDto;

import com.tsu.projectX.entities.Team;
import com.tsu.projectX.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tsu.projectX.config.AuthConfig.ROLE_COACH;
import static com.tsu.projectX.config.AuthConfig.ROLE_MANAGER;

@Data
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
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setNickname(user.getNickname());
        userResponseDto.setCountry(user.getCountry());
        userResponseDto.setPassword(user.getPassword());

        String role = user.getRole().getName();
        String team;
        if (ROLE_COACH.equals(role)) {
            team = user.getCouchingTeam() == null ? null : user.getCouchingTeam().getName();
        } else if (ROLE_MANAGER.equals(role)) {
            team = user.getManagingTeam() == null ? null : user.getManagingTeam().getName();
        } else {
            team = user.getTeam() == null ? null : user.getTeam().getName();
        }
        userResponseDto.setTeam(team);
        userResponseDto.setWantedTeam(user.getWantedTeam());
        userResponseDto.setRole(role);
        userResponseDto.setWantedRole(user.getWantedRole());
        return userResponseDto;
    }

    public static List<UserResponseDto> fromListUser(List<User> users) {
        List<UserResponseDto> usersResponse = new ArrayList<>();
        users.forEach(user -> usersResponse.add(fromUser(user)));
        return usersResponse;
    }
}
