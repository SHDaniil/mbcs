package com.tsu.projectX.services.interfaces;

import com.tsu.projectX.data.responseDto.AuthResponse;
import com.tsu.projectX.data.UserLogin;
import com.tsu.projectX.data.UserRegiter;

import java.util.UUID;

public interface IAuthService {

    boolean checkAuthAndPermission(UUID authToken, String... roles);
    AuthResponse login(UserLogin userLogin);
    AuthResponse registerNewUserAccount(UserRegiter userRegiter);
}
