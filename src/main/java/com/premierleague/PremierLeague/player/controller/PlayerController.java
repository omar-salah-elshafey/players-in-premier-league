package com.premierleague.PremierLeague.player.controller;


import com.premierleague.PremierLeague.player.dto.PlayerDTO;
import com.premierleague.PremierLeague.player.model.Player;
import com.premierleague.PremierLeague.player.repository.PlayerRepository;
import com.premierleague.PremierLeague.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addPlayer(@RequestBody PlayerDTO playerDTO){
        Optional<Player> existingPlayer = playerRepository.findByName(playerDTO.getName());
        if (existingPlayer.isEmpty()){
            playerService.addPlayer(playerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Player added successfully :)");
        }
        else return ResponseEntity.status(HttpStatus.OK).body("Player already exists!");

    }

    @GetMapping("/get")
    public ResponseEntity<List<PlayerDTO>> getPlayers(){
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayers());
    }


    @PutMapping("/update")
    public ResponseEntity<String> updatePlayer(@RequestBody PlayerDTO playerDTO) {
        Optional<Player> existingPlayer = playerRepository.findByName(playerDTO.getName());
        if (existingPlayer.isPresent()){
            playerService.updatePlayer(playerDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Player updated successfully :)");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There's no Players named: "+ playerDTO.getName()+" :(");

    }

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        Optional<Player> existingPlayer = playerRepository.findByName(playerName);
        if (existingPlayer.isPresent()){
            playerService.deletePlayer(playerName);
            return ResponseEntity.status(HttpStatus.OK).body("Player deleted  successfully.");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not Found :(");

    }
}
