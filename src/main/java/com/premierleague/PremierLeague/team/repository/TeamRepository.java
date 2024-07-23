package com.premierleague.PremierLeague.team.repository;

import com.premierleague.PremierLeague.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findByName(String teamName);
    void deleteByName(String teamName);
}
