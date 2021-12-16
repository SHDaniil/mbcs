package com.tsu.projectX.entities;

import lombok.Data;

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
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String team;

    private UUID authToken;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(
            String nickname,
            String name,
            String lastName,
            String email,
            String password,
            String team,
            UUID authToken,
            Role role) {
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.team = team;
        this.authToken = authToken;
        this.role = role;
    }

    public User() {
    }
}
