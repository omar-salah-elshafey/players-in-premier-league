package com.premierleague.PremierLeague.team.mapper;

import com.premierleague.PremierLeague.team.dto.TeamDTO;
import com.premierleague.PremierLeague.team.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    Team teamDTOToTeam(TeamDTO teamDTO);
    void updateTeam(TeamDTO teamDTO, @MappingTarget Team team);
    TeamDTO teamToTeamDTO(Team team);
}
