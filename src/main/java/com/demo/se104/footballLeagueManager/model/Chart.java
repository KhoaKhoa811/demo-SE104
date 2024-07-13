package com.demo.se104.footballLeagueManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Chart {

	private Integer id;
	private Integer teamId;
	private String teamName;
	private String win;
	private String draw;
	private String loss;
	private String goalDifference;
	private String rank;
}
