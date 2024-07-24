package com.premierleague.PremierLeague.team.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {

    private String name;
    private String nation;
    private String location;
    private String manager;
}
