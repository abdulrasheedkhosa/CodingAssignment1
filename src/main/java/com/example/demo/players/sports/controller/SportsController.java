package com.example.demo.players.sports.controller;

import com.example.demo.players.sports.dto.SportsDto;
import com.example.demo.players.sports.dto.SportsPlayersDto;
import com.example.demo.players.sports.service.SportsServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SportsController {

    @Autowired
    private SportsServiceImpl sportsService;
    @PostMapping("/sport")
    public SportsDto saveSport(@RequestBody SportsDto sport) {
        return sportsService.saveSport(sport);
    }
    @GetMapping("/sports")
    public List<SportsDto> getAllSports() {
        return sportsService.fetchAllSports();
    }

    //(B-4) Create an API endpoint to delete sports and its associated data.
    @DeleteMapping("/deleteSport/{name}")
    public String deletedSport(@PathVariable String name) {
        return sportsService.deleteSports(name);
    }

   // (B-5) Please make a paginated player list with category sports filter. Pagination amount should be by 10.
    @GetMapping("/getSportPlayersBySportsCategory")
    public List<SportsPlayersDto> findAllSportsPlayersBySportsCategory(@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam String name ) {
        return sportsService.findAllSportsPlayersBySportsCategory(pageSize,name);
    }

}