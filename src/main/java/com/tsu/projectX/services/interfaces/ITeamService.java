package com.tsu.projectX.services.interfaces;

import com.tsu.projectX.data.responseDto.TeamResponseDto;
import com.tsu.projectX.data.responseDto.UserResponseDto;
import com.tsu.projectX.entities.Team;

import java.util.List;
import java.util.UUID;

public interface ITeamService {

    TeamResponseDto get(UUID id);

    List<TeamResponseDto> getAll();

    Team addPlayer(UUID id);
}
