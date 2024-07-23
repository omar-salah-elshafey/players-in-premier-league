package com.premierleague.PremierLeague.player.model;

import com.premierleague.PremierLeague.team.model.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @Column(name = "name", unique = true)
    private String name;
    private Integer age;
    private String nationality;
    @ManyToOne
    @JoinColumn(name = "team_name")
    private Team team;
    private String position;
    private Integer redCards;
    private Integer yellowCards;
}
