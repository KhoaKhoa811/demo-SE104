package com.demo.se104.footballLeagueManager.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegulationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
	
	@Column(name="min_age")
	private Integer minAge;
	
	@Column(name="max_age")
	private Integer maxAge;
	
	@Column(name="min_number")
	private Integer minNumber;
	
	@Column(name="max_number")
	private Integer maxNumber;
	
	@Column(name="max_foreign_number")
	private Integer maxForeignNumber;
	
	@Column(name="number_of_goal_type")
	private Integer numberOfGoalType;
	
	@Column(name="max_score_time")
	private String maxScoreTime;
	
	@Column(name="win_point")
	private Integer winPoint;
	
	@Column(name="draw_point")
	private Integer drawPoint;
	
	@Column(name="loss_point")
	private Integer lossPoint;
	
	@Column(name="ranking_order")
	private String rankingOrder;
}
