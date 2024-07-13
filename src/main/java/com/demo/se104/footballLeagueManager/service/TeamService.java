package com.demo.se104.footballLeagueManager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Team;

public interface TeamService {

	List<Team> findAll();

	Team findById(Integer theId);

	TeamEntity save(Team theTeam);

    void deleteById(Integer theId);
    
    Integer countAll();
    
    TeamEntity findByName(String name);
    
    Page<Team> findAll(Pageable pageable);
    
    
}
