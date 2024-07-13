package com.demo.se104.footballLeagueManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PlayerSearch {

	private Integer id;
	private String name;
	private String team;
	private String playerType;
	private String position;
	private Integer goal;
}
