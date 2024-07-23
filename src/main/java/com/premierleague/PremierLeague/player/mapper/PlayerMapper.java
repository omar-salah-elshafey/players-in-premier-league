package com.premierleague.PremierLeague.player.mapper;

import com.premierleague.PremierLeague.player.dto.PlayerDTO;
import com.premierleague.PremierLeague.player.model.Player;
import com.premierleague.PremierLeague.team.model.Team;
import com.premierleague.PremierLeague.team.repository.TeamRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

//@Mapper(componentModel = "spring")
//public interface PlayerMapper {
//
//    PlayerDTO PlayertoPlayerDTO(Player player);
//
//    Player PlayerDTOtoPlayer(PlayerDTO playerDTO);
//
//    void updatePlayer(PlayerDTO playerDTO,@MappingTarget Player player);
//
//}

@Mapper(componentModel = "spring", uses = TeamRepository.class)
public abstract class PlayerMapper {

    @Autowired
    private TeamRepository teamRepository;

    @Mapping(target = "team", source = "teamName", qualifiedByName = "teamNameToTeam")
    public abstract Player PlayerDTOtoPlayer(PlayerDTO playerDTO);

    @Mapping(target = "teamName", source = "team.name")
    public abstract PlayerDTO PlayertoPlayerDTO(Player player);

    @Named("teamNameToTeam")
    Team teamNameToTeam(String teamName) {
        return teamRepository.findByName(teamName).orElse(null);
    }

    public abstract void updatePlayer(PlayerDTO playerDTO, @MappingTarget Player player);
}

