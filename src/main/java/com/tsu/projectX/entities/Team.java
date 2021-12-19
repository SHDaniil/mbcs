package com.tsu.projectX.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "team_table")
public class Team {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String country;
    private String ranking;
    private String topTime;
    private String averageAge;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<User> players;

    @OneToOne(mappedBy = "couchingTeam")
    private User coach;
    @OneToOne(mappedBy = "managingTeam")
    private User manager;
}
