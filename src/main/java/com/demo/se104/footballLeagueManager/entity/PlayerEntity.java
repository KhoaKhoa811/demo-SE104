package com.demo.se104.footballLeagueManager.entity;

import java.util.Date;

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
@Table(name="player")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="name")
    private String name;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="position")
    private String position;
    
    @Column(name="goal")
    private Integer goal = 0;
    
    @ManyToOne(cascade= {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private TeamEntity team;
    
    @ManyToOne(cascade= {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "player_type_id", referencedColumnName = "id")
    private PlayerTypeEntity playerType;
}
