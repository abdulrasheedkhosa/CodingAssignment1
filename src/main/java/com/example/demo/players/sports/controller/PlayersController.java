package com.example.demo.players.sports.controller;


import com.example.demo.players.sports.dto.PlayersDto;
import com.example.demo.players.sports.dto.SportsPlayersDto;
import com.example.demo.players.sports.service.PlayersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlayersController {
    private PlayersServiceImpl playersService;

    // API to create new player
    @PostMapping("/player")
    public PlayersDto savePlayer(@RequestBody PlayersDto player) {
        return playersService.savePlayer(player);
    }

    // APT to fetch all players
    @GetMapping("/players")
    public List<PlayersDto> getAllPlayers() {
        return playersService.fetchAllPlayers();
    }


    // (B-1) Create an API endpoint to return sports with given names (1 or more names) in JSON format.
    // The associated players should also be returned. It should cover failure cases as well.
    @GetMapping("/sportsPlayers")
    public List<SportsPlayersDto> getAllSportPlayers() {
        return playersService.fetchAllSportsPlayers();
    }

    //(B-2) Create an API endpoint to return players with no sports.
    @GetMapping("/playerWithoutSports")
    public List<PlayersDto> findAllPlayersWithNotAssociateWithSports() {
        return playersService.findAllPlayersWithNotAssociateWithSports();
    }


    //(B-3) Create an API endpoint to update player sports
    @PutMapping("/updateSportsOfPlayer/{playerName}/{sportsName}")
    public String deletedSport(@PathVariable String playerName,@PathVariable String sportsName) {
        return playersService.updateSportsOfPlayer(sportsName,playerName);
    }
}