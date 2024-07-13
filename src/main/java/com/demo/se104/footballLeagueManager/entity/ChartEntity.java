package com.demo.se104.footballLeagueManager.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="chart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChartEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
	
	@Column(name="win")
	private Integer win = 0;
	
	@Column(name="draw")
	private Integer draw = 0;
	
	@Column(name="loss")
	private Integer loss = 0;
	
	@Column(name="goal_difference")
	private Integer goalDifference = 0;
	
	@Column(name="chart_rank")
	private Integer rank = 0;
	
	@OneToOne(cascade= {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "team_id", referencedColumnName = "id")
	private TeamEntity team;
}
