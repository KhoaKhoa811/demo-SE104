package com.demo.se104.footballLeagueManager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.se104.footballLeagueManager.entity.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {

	List<PlayerEntity> findByTeamId(Integer teamId);
	
	List<PlayerEntity> findByNameContainingIgnoreCase(String name);
	
	Page<PlayerEntity> findByTeamId(Integer teamId, Pageable pageable);
	
	Page<PlayerEntity> findAll(Pageable pageable);
	
	Page<PlayerEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
	
	long countByTeamId(Integer teamId);
	
	long countByPlayerTypeIdAndTeamId(Integer playerTypeId, Integer teamId);
}
