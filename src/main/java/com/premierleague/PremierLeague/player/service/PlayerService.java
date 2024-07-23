package com.premierleague.PremierLeague.player.service;

import com.premierleague.PremierLeague.player.dto.PlayerDTO;
import com.premierleague.PremierLeague.player.mapper.PlayerMapper;
import com.premierleague.PremierLeague.player.model.Player;
import com.premierleague.PremierLeague.player.repository.PlayerRepository;
import com.premierleague.PremierLeague.team.exception.TeamNotFoundException;
import com.premierleague.PremierLeague.team.model.Team;
import com.premierleague.PremierLeague.team.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;

    public void addPlayer(PlayerDTO playerDTO){
        Team team = teamRepository.findByName(playerDTO.getTeamName())
                .orElseThrow(() -> new TeamNotFoundException("There is no team named: "+ playerDTO.getTeamName()));
        Player player = playerMapper.PlayerDTOtoPlayer(playerDTO);
        player.setTeam(team);
        playerRepository.save(playerMapper.PlayerDTOtoPlayer(playerDTO));
    }

    public List<PlayerDTO> getPlayers(){
        return playerRepository.findAll().stream().map(playerMapper::PlayertoPlayerDTO).toList();
    }

    public void updatePlayer(PlayerDTO playerDTO) {
        Optional<Player> existingPlayer = playerRepository.findByName(playerDTO.getName());

        if (existingPlayer.isPresent()) {
            Player playerUpdate = existingPlayer.get();
            Team team = teamRepository.findByName(playerDTO.getTeamName())
                    .orElseThrow(() -> new TeamNotFoundException("There is no team named: "+ playerDTO.getTeamName()));
            playerMapper.updatePlayer(playerDTO, playerUpdate);
            playerUpdate.setTeam(team);
            playerRepository.save(playerUpdate);
        }
    }

//    public void updatePlayer(PlayerDTO playerDTO) throws PlayerNotFoundException {
//        Optional<Player> existingPlayer = playerRepository.findByName(playerDTO.getName());
//
//        if (existingPlayer.isPresent()) {
//            Player playerUpdate = existingPlayer.get();
//            playerMapper.updatePlayer(playerDTO, playerUpdate);
//            playerRepository.save(playerUpdate);
//        } else {
//            throw new PlayerNotFoundException("Player not found with name: " + playerDTO.getName());
//        }
//    }

    @Transactional
    public void deletePlayer(String playerName){
        Optional<Player> existingPlayer = playerRepository.findByName(playerName);
        if (existingPlayer.isPresent()) {
            playerRepository.deleteByName(playerName);
        }

    }
}
