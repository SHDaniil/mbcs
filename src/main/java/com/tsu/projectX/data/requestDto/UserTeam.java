package com.tsu.projectX.data.requestDto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserTeam {

    private UUID teamId;
    private UUID userId;
}
