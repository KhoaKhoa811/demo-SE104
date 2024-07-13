package com.demo.se104.footballLeagueManager.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="date_time")
	private String dateTime;
	
	@Column(name="home_ground")
	private String homeGround;
	
	@Column(name="team_1_goal")
	private Integer team1Goal = 0;
	
	@Column(name="team_2_goal")
	private Integer team2Goal = 0;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "round_id", referencedColumnName = "id")
	private RoundEntity round;
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="match_team",
			joinColumns=@JoinColumn(name="match_id"),
			inverseJoinColumns=@JoinColumn(name="team_id")
			)
	private List<TeamEntity> teams;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
	private List<ReportEntity> reports;
}
