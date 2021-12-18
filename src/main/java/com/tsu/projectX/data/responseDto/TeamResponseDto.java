package com.tsu.projectX.data.responseDto;

import com.tsu.projectX.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TeamResponseDto {

    private UUID id;
    private String name;
    private String country;
    private String ranking;
    private String topTime;
    private String averageAge;
    private List<UserResponseDto> players;
    private UserResponseDto coach;
    private UserResponseDto manager;

    public static TeamResponseDto fromTeam(Team team) {
        return new TeamResponseDto(
                team.getId(),
                team.getName(),
                team.getCountry(),
                team.getRanking(),
                team.getTopTime(),
                team.getAverageAge(),
                team.getPlayers() == null ? null : UserResponseDto.fromListUser(team.getPlayers()),
                team.getCoach() == null ? null : UserResponseDto.fromUser(team.getCoach()),
                team.getManager() == null ? null : UserResponseDto.fromUser(team.getManager())
        );
    }

    public static List<TeamResponseDto> fromListTeam(List<Team> teams) {
        List<TeamResponseDto> teamsResponse = new ArrayList<>();
        teams.forEach(team -> teamsResponse.add(fromTeam(team)));
        return teamsResponse;
    }
}
