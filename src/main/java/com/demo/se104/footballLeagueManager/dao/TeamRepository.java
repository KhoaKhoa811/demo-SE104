package com.demo.se104.footballLeagueManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.se104.footballLeagueManager.entity.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

	TeamEntity findByName(String name);
	
	Page<TeamEntity> findAll(Pageable pageable);
}
