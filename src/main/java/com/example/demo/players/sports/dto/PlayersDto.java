package com.example.demo.players.sports.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlayersDto {
    private Integer player_id;
    private String email;
    private Integer level;
    private Integer age;
    private String  gender;
}
