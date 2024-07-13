package com.demo.se104.footballLeagueManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Player {

	private Integer id;
	private String name;
	private String birthday;
	private String playerType;
	private Integer playerTypeId;
	private String position;
	private Integer teamId;
	private String teamName;
	private Integer goal;
}
