package com.example.demo.players.sports.repository;


import com.example.demo.players.sports.model.Players;
import com.example.demo.players.sports.model.Sports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsRepository extends JpaRepository<Sports,Long> {

    @Query(value="select * from sports where name = :sportsName", nativeQuery=true)
    Sports findSportsByName(String sportsName);

}
