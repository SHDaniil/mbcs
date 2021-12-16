package com.tsu.projectX.data;

import lombok.Data;

@Data
public class UserRegiter {

    private String nickname;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirm;
}
