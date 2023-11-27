package com.example.demo.players.sports.service;


import com.example.demo.players.sports.dto.SportsPlayersDto;
import com.example.demo.players.sports.model.Players;
import com.example.demo.players.sports.model.Sports;
import com.example.demo.players.sports.model.SportsPlayers;
import com.example.demo.players.sports.repository.PlayersRepository;
import com.example.demo.players.sports.repository.SportsPlayersRepository;
import com.example.demo.players.sports.repository.SportsRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;
import com.example.demo.players.sports.dto.PlayersDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlayersServiceImpl implements PlayersService {
    private PlayersRepository playersRepository;
	private SportsRepository sportsRepository;
	private SportsPlayersRepository sportsPlayersRepository;
	private ModelMapper modelMapper;
	@Override
	public PlayersDto savePlayer(PlayersDto playerDto) {
		Players player = modelMapper.map(playerDto,Players.class);
		return modelMapper.map(playersRepository.save(player),PlayersDto.class);
	}

	@Override
	public List<SportsPlayersDto> fetchAllSportsPlayers() {
		return playersRepository.findAllSportsPlayers();
	}

	@Override
	 public List<PlayersDto> fetchAllPlayers() {
		List<Players> allPlayers = this.playersRepository.findAll();
	    List<PlayersDto> dtos = allPlayers
				 .stream()
				 .map(Players -> modelMapper.map(Players, PlayersDto.class))
				 .collect(Collectors.toList());
	 return  dtos;

	}
	@Override
	public List<PlayersDto> findAllPlayersWithNotAssociateWithSports() {
		return playersRepository.findAllPlayersWithNotAssociateWithSports();
	}

	@Override
	public String updateSportsOfPlayer(String sportsName, String playerName) {
		Sports sports = sportsRepository.findSportsByName(sportsName);
		Players players = playersRepository.findPlayerByName(playerName);

		if(sports != null && sports.getSport_id() != null && sports.getSport_id() > 0
		&& players != null && players.getPlayer_id() != null && players.getPlayer_id() > 0){
			SportsPlayers sportsPlayers = sportsPlayersRepository.findSportsPlayersBySportsNamePlayerName(sports.getSport_id(),players.getPlayer_id());

			if(sportsPlayers != null && sportsPlayers.sports_payers_id >0){
               return "Sports already associated with player";
			} else {
				List<SportsPlayers> sportsPlayersList = sportsPlayersRepository.findListOfSportsPlayer(players.getPlayer_id());

				if((sportsPlayersList.size()+1) > 0 && (sportsPlayersList.size()+1) < 2){
                    Integer id = sportsPlayersList.get(0).getSports_payers_id();
					Optional<SportsPlayers> result = sportsPlayersRepository.findById(Long.parseLong(sportsPlayersList.get(0).getSports_payers_id().toString()));
					SportsPlayers object = modelMapper.map(result.get(), SportsPlayers.class);
					object.setPlayer_id(players.getPlayer_id());
					object.setSport_id(sports.getSport_id());
					sportsPlayersRepository.save(object);
					return  "Update Sports successfully.";
				}
				SportsPlayers  addSportsPlayers = new SportsPlayers();
				addSportsPlayers.setPlayer_id(players.getPlayer_id());
				addSportsPlayers.setSport_id(sports.getSport_id());
				sportsPlayersRepository.save(addSportsPlayers);
				return  "Sports associate successfully.";
			}
		}
		return "Either Sports or Player does not exist.";
	}
}
