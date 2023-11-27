package com.example.demo.players.sports.service;


import com.example.demo.players.sports.dto.PlayersDto;
import com.example.demo.players.sports.dto.SportsPlayersDto;

import java.util.List;
import java.util.Optional;

public interface PlayersService {
    PlayersDto savePlayer(PlayersDto playerDto);
    List<PlayersDto> fetchAllPlayers();
    List<SportsPlayersDto> fetchAllSportsPlayers();
    List<PlayersDto>  findAllPlayersWithNotAssociateWithSports();

    String updateSportsOfPlayer(String sportsName, String playerName);

}
