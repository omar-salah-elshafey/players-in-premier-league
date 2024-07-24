package com.premierleague.PremierLeague.team.controller;

import com.premierleague.PremierLeague.team.dto.TeamDTO;
import com.premierleague.PremierLeague.team.model.Team;
import com.premierleague.PremierLeague.team.repository.TeamRepository;
import com.premierleague.PremierLeague.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    @PostMapping("/add")
    public ResponseEntity<String> addTeam(@RequestBody TeamDTO teamDTO){
        Optional<Team> existingTeam = teamRepository.findByName(teamDTO.getName());
        if (existingTeam.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body("Team already exists!");
        }
        else {
            teamService.addTeam(teamDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team added successfully :)");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<TeamDTO>> getTeams(){
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeams());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTeam(@RequestBody TeamDTO teamDTO) {
        Optional<Team> existingTeam = teamRepository.findByName(teamDTO.getName());
        if (existingTeam.isPresent()){
            teamService.updateTeam(teamDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Team Updated Successfully :)");
        }
        else {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There's no teams named: "+ teamDTO.getName()+" :(");
        }
    }


@DeleteMapping("/delete/{teamName}")
public ResponseEntity<String> deleteTeam(@PathVariable String teamName){
    Optional<Team> existingTeam = teamRepository.findByName(teamName);
    if (existingTeam.isPresent()){
        teamService.deleteTeam(teamName);
        return ResponseEntity.status(HttpStatus.OK).body("Team deleted  successfully.");
    }
    else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not Found :(");

}

}