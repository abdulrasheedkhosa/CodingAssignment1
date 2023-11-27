package com.example.demo.players.sports.repository;



import com.example.demo.players.sports.model.SportsPlayers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportsPlayersRepository extends JpaRepository<SportsPlayers,Long> {

    @Query(value="select * from sports_players where sport_id = :sportsId", nativeQuery=true)
    List<SportsPlayers> findSportsPlayersByName(Integer sportsId);

    @Query(value="select * from sports_players where sport_id = :sportsId and player_id = :playerId", nativeQuery=true)
    SportsPlayers findSportsPlayersBySportsNamePlayerName(Integer sportsId, Integer playerId);

    @Query(value="select * from sports_players where player_id = :playerId", nativeQuery=true)
    List<SportsPlayers> findListOfSportsPlayer(Integer playerId);

}
