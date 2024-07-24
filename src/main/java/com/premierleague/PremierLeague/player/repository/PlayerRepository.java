package com.premierleague.PremierLeague.player.repository;

import com.premierleague.PremierLeague.player.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional <Player>findByName(String playerName);
    void deleteByName(String playerName);

    List<Player> findByTeamName(String teamName);
}
