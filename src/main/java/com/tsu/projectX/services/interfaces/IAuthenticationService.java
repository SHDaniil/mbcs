package com.tsu.projectX.services.interfaces;

import com.tsu.projectX.data.UserLogin;
import com.tsu.projectX.data.UserRegiter;

import java.util.UUID;

public interface IAuthenticationService {

    boolean checkAuthToken(UUID authToken);
    UUID login(UserLogin userLogin);
    boolean registerNewUserAccount(UserRegiter userRegiter);
}
