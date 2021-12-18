package com.tsu.projectX.data.requestDto;

import com.tsu.projectX.entities.User;
import lombok.Data;

@Data
public class UserRequestDto {

    private String nickname;
    private String country;
    private String password;
    private String team;
    private String role;

    public void modifyUser(User user) {
        user.setNickname(nickname);
        user.setCountry(country);
        user.setPassword(password);
        user.setTeam(team);
    }

}
