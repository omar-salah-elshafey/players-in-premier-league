package com.premierleague.PremierLeague.player.dto;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    @Id
    private String name;
    @NonNull
    private String nationality;
    @NonNull
    private String position;
    @NonNull
    private String team;
    @NonNull
    private Integer age;

    private Integer yellowCards;
    private Integer redCards;
}
