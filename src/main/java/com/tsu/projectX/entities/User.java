package com.tsu.projectX.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    private String nickname;
    private String country;
    private String password;
    private String team;

    private UUID authToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    private String wantedRole;

    public User(
            String nickname,
            String country,
            String password,
            String team,
            UUID authToken,
            Role role) {
        this.nickname = nickname;
        this.country = country;
        this.password = password;
        this.team = team;
        this.authToken = authToken;
        this.role = role;
    }

    public User() {
    }
}
