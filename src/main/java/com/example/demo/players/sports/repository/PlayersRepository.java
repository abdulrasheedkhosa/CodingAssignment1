package com.example.demo.players.sports.repository;


import com.example.demo.players.sports.dto.PlayersDto;
import com.example.demo.players.sports.dto.SportsPlayersDto;
import com.example.demo.players.sports.model.Players;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PlayersRepository extends JpaRepository<Players,Long> {
    @Query("select new com.example.demo.players.sports.dto.SportsPlayersDto(p.email, s.name)"
            + "from "
            + "SportsPlayers sp "
            + "inner join "
            + "Players p "
            + "on p.player_id = sp.player_id "
            + "inner join "
            + "Sports s "
            + "on s.sport_id = sp.sport_id ")
    List<SportsPlayersDto> findAllSportsPlayers();

    @Query("select new com.example.demo.players.sports.dto.PlayersDto(p.player_id,p.email,p.level,p.age,p.gender)"
            + " from Players p"
            + " where p.player_id NOT IN"
            + " (select sp.player_id from SportsPlayers sp)")
    List<PlayersDto> findAllPlayersWithNotAssociateWithSports();

    @Query(value="select * from players where email = :name", nativeQuery=true)
    Players findPlayerByName(String name);


    @Query("select new com.example.demo.players.sports.dto.SportsPlayersDto(p.email, s.name)"
            + "from "
            + "SportsPlayers sp "
            + "inner join "
            + "Players p "
            + "on p.player_id = sp.player_id "
            + "inner join "
            + "Sports s "
            + "on s.sport_id = sp.sport_id and s.name IN :sportsName ")
    Page<SportsPlayersDto> findAllSportsPlayersBySportsCategory(String sportsName, Pageable pageable);

}
