package com.tsu.projectX.repositories;

import com.tsu.projectX.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITeamRepository extends JpaRepository<Team, UUID> {

    Team findByName(String name);
}
