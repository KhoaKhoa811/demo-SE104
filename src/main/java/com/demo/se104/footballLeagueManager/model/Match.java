package com.demo.se104.footballLeagueManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Match {

	private Integer id;
	private String team1Id;
	private String team1Name;
	private String team2Id;
	private String team2Name;
	private String dateTime;
	private String homeGround;
	private String roundId;
	private String roundName;
	private Integer team1Goal;
	private Integer team2Goal;
}
