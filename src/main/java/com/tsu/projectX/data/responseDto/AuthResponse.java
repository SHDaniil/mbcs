package com.tsu.projectX.data.responseDto;

import com.tsu.projectX.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthResponse {

    private UUID authToken;
    private String role;

    public static AuthResponse fromUser(User user) {
        return new AuthResponse(
                user.getAuthToken(),
                user.getRole().getName()
        );
    }
}
