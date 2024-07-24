package com.premierleague.PremierLeague.team.service;

import com.premierleague.PremierLeague.team.dto.TeamDTO;
import com.premierleague.PremierLeague.team.mapper.TeamMapper;
import com.premierleague.PremierLeague.team.model.Team;
import com.premierleague.PremierLeague.team.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public void addTeam(TeamDTO teamDTO) {
        teamRepository.save(teamMapper.teamDTOToTeam(teamDTO));
    }

    public List<TeamDTO> getTeams() {
        return teamRepository.findAll().stream().map(teamMapper::teamToTeamDTO).toList();
    }

    public void updateTeam(TeamDTO teamDTO){
        Optional<Team> existingTeam = teamRepository.findByName(teamDTO.getName());

        if (existingTeam.isPresent()) {
            Team teamToUpdate = existingTeam.get();
            teamMapper.updateTeam(teamDTO, teamToUpdate);
            teamRepository.save(teamToUpdate);
        }
    }

    @Transactional
    public void deleteTeam(String teamName) {
        Optional<Team> existingTeam = teamRepository.findByName(teamName);

        if (existingTeam.isPresent()) {
            teamRepository.deleteByName(teamName);
        }
    }
}
