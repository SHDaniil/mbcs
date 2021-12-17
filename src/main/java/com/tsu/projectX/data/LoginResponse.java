package com.tsu.projectX.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class LoginResponse {

    private UUID authToken;
    private String role;
}
