package com.example.demo.players.sports.service;


import com.example.demo.players.sports.dto.SportsDto;
import com.example.demo.players.sports.dto.SportsPlayersDto;

import java.util.List;

public interface SportsService {
    SportsDto saveSport(SportsDto sportsDto);
    List<SportsDto> fetchAllSports();

    String deleteSports(String name);

    public List<SportsPlayersDto> findAllSportsPlayersBySportsCategory(Integer pageSize, String sportName);

}
