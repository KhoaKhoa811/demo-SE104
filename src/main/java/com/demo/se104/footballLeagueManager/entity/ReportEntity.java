package com.demo.se104.footballLeagueManager.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="reporter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
	
	@Column(name="time")
	private String time;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "player_id", referencedColumnName = "id")
	private PlayerEntity player;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "goal_type_id", referencedColumnName = "id")
	private GoalTypeEntity goalType;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "match_id", referencedColumnName = "id")
	private MatchEntity match;
}
