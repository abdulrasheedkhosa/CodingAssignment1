package com.example.demo.players.sports.service;


import com.example.demo.players.sports.dto.SportsDto;
import com.example.demo.players.sports.dto.SportsPlayersDto;
import com.example.demo.players.sports.model.Sports;
import com.example.demo.players.sports.model.SportsPlayers;
import com.example.demo.players.sports.repository.PlayersRepository;
import com.example.demo.players.sports.repository.SportsPlayersRepository;
import com.example.demo.players.sports.repository.SportsRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SportsServiceImpl implements SportsService {
    private SportsRepository sportsRepository;
	private PlayersRepository playersRepository;
	private SportsPlayersRepository sportsPlayersRepository;

	private ModelMapper modelMapper;
	@Override
	public SportsDto saveSport(SportsDto sportsDto) {
		Sports sport = modelMapper.map(sportsDto,Sports.class);
		return modelMapper.map(sportsRepository.save(sport),SportsDto.class);
	}

	@Override
	public String deleteSports(String name) {
		Sports sports = sportsRepository.findSportsByName(name);
		if(sports != null && sports.getSport_id() != null && sports.getSport_id() > 0){
			List<SportsPlayers> sportsPlayers = sportsPlayersRepository.findSportsPlayersByName(sports.getSport_id());
			if(sportsPlayers != null && sportsPlayers.size() > 0)
				sportsPlayersRepository.deleteAll(sportsPlayers);
			sportsRepository.delete(sports);
			return  "Sports deleted Successfully!";
		}
		return "Sports not available by giving name.";
	}

	@Override
	 public List<SportsDto> fetchAllSports() {
		List<Sports> allSports = this.sportsRepository.findAll();
	    List<SportsDto> dtos = allSports
				 .stream()
				 .map(Sports -> modelMapper.map(Sports, SportsDto.class))
				 .collect(Collectors.toList());
	 return  dtos;

	}

	@Override
	public List<SportsPlayersDto> findAllSportsPlayersBySportsCategory(Integer pageSize, String sportName) {
		Pageable paging = PageRequest.ofSize(pageSize);
		Page<SportsPlayersDto> pagedResult = playersRepository.findAllSportsPlayersBySportsCategory(sportName,paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<SportsPlayersDto>();
		}
	}
}
