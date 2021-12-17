package com.tsu.projectX.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "role_table")
public class Role {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }
}
