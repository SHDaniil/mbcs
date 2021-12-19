package com.tsu.projectX.services.interfaces;

import com.tsu.projectX.data.responseDto.TeamResponseDto;
import com.tsu.projectX.entities.Team;

import java.util.List;
import java.util.UUID;

public interface ITeamService {

    TeamResponseDto get(UUID id);

    List<TeamResponseDto> getAll();

    TeamResponseDto addCoach(UUID teamId, UUID userId);

    TeamResponseDto addManager(UUID teamId, UUID userId);

    Team addPlayer(UUID id);
}
