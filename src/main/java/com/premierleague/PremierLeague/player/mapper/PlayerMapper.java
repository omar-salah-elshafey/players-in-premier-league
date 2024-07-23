package com.premierleague.PremierLeague.player.mapper;

import com.premierleague.PremierLeague.player.dto.PlayerDTO;
import com.premierleague.PremierLeague.player.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDTO PlayertoPlayerDTO(Player player);

    Player PlayerDTOtoPlayer(PlayerDTO playerDTO);

    void updatePlayer(PlayerDTO playerDTO,@MappingTarget Player player);

}
