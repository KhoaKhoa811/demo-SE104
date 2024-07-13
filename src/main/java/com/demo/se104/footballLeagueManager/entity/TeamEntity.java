package com.demo.se104.footballLeagueManager.entity;

import java.util.List;

import com.demo.se104.footballLeagueManager.model.Player;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="home_ground")
	private String homeGround;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
	List<PlayerEntity> player;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "team")
	ChartEntity chart;
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinTable(
			name="match_team",
			joinColumns=@JoinColumn(name="team_id"),
			inverseJoinColumns=@JoinColumn(name="match_id")
			)
	private List<MatchEntity> matches;
}
