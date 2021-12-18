package com.tsu.projectX.data;

import com.tsu.projectX.entities.User;
import lombok.Data;

@Data
public class UserRegiter {

    private String nickname;
    private String country;
    private String wantedRole;
    private String password;
    private String passwordConfirm;

    public User toUser() {
        User user = new User();
        user.setNickname(nickname);
        user.setCountry(country);
        user.setWantedRole(wantedRole);
        user.setPassword(password);
        return user;
    }
}
