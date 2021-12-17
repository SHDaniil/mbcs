package com.tsu.projectX.services.interfaces;

import com.tsu.projectX.data.LoginResponse;
import com.tsu.projectX.data.UserLogin;
import com.tsu.projectX.data.UserRegiter;

import java.util.UUID;

public interface IAuthService {

    boolean checkAuthToken(UUID authToken);
    LoginResponse login(UserLogin userLogin);
    boolean registerNewUserAccount(UserRegiter userRegiter);
}
