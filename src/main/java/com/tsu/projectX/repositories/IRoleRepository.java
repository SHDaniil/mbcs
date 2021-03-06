package com.tsu.projectX.repositories;

import com.tsu.projectX.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);
}
