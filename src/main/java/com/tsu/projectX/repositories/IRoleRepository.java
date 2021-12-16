package com.tsu.projectX.repositories;

import com.tsu.projectX.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);
}
