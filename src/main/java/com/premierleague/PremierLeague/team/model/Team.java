package com.premierleague.PremierLeague.team.model;

import com.premierleague.PremierLeague.player.model.Player;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    private String name;
    private String nation;
    private String location;
    private String manager;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
