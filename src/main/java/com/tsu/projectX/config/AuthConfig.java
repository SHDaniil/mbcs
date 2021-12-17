package com.tsu.projectX.config;

import java.util.ArrayList;
import java.util.List;

public class AuthConfig {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_PLAYER = "ROLE_PLAYER";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_COACH = "ROLE_COACH";

    public static final List<String> ROLES = new ArrayList<>();
    static {
        ROLES.add(ROLE_ADMIN);
        ROLES.add(ROLE_USER);
        ROLES.add(ROLE_PLAYER);
        ROLES.add(ROLE_MANAGER);
        ROLES.add(ROLE_COACH);
    }
}
