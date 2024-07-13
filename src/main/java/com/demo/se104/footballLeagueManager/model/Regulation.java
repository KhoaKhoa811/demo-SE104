package com.demo.se104.footballLeagueManager.model;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Regulation {

    private Integer id;
	private Integer minAge;
	private Integer maxAge;
	private Integer minNumber;
	private Integer maxNumber;
	private Integer maxForeignNumber;
	private Integer numberOfGoalType;
	private String maxScoreTime;
	private Integer winPoint;
	private Integer drawPoint;
	private Integer lossPoint;
	private String rankingOrder;
}
