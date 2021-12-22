package com.tsu.projectX.services.interfaces;

import com.tsu.projectX.data.responseDto.TeamResponseDto;
import com.tsu.projectX.entities.Team;

import java.util.List;
import java.util.UUID;

public interface ITeamService {

    TeamResponseDto get(UUID id);

    List<TeamResponseDto> getAll();

    boolean addCoach(UUID teamId, UUID userId);

    boolean addManager(UUID teamId, UUID userId);

    boolean addPlayer(UUID teamId, UUID userId);

    boolean deleteCoach(UUID teamId, UUID userId);

    boolean deleteManager(UUID teamId, UUID userId);

    boolean deletePlayer(UUID teamId, UUID userId);

    boolean compare(Team team, UUID teamId);
}
