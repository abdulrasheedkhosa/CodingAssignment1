package com.example.demo.players.sports.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SportsPlayers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer sports_payers_id;
    public Integer player_id;
    public Integer sport_id;

}
