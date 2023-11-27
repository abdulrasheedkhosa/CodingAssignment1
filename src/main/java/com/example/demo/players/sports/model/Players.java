package com.example.demo.players.sports.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Players {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer player_id;
    private String email;
    private Integer level;
    private Integer age;
    private String  gender;
	
}
