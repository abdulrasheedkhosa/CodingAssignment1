package com.example.demo.players.sports.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SportsPlayersDto {
    private String  player_name;
    private String  sport_name;
}
