package com.tsu.projectX.data.requestDto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddUserTeam {

    private UUID teamId;
    private UUID userId;
}
