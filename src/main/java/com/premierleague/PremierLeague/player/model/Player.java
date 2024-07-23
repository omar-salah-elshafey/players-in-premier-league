package com.premierleague.PremierLeague.player.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    private String name;
    private Integer age;
    private String nationality;
    private String team;
    private String position;
    private Integer redCards;
    private Integer yellowCards;
}
