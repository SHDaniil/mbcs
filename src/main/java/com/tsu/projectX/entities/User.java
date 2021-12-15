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
    private String team;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
