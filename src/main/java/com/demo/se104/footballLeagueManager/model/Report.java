package com.demo.se104.footballLeagueManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Report {

	private Integer id;
	private String playerId;
	private String playerName;
	private String team;
	private String goalTypeId;
	private String goalTypeName;
	private String time;
	private Integer matchId;
}
