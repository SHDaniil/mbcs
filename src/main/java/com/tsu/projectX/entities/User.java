package com.tsu.projectX.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    private String nickname;
    private String country;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    private String wantedTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    private String wantedRole;

    private UUID authToken;
}
