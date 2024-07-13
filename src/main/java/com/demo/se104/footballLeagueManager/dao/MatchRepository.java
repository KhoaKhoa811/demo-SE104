package com.demo.se104.footballLeagueManager.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.se104.footballLeagueManager.entity.MatchEntity;

public interface MatchRepository extends JpaRepository<MatchEntity, Integer>{

	List<MatchEntity> findByRoundId(Integer roundId);
	
	List<MatchEntity> findAllByOrderByDateTimeDesc(Pageable pageable);
	
	Page<MatchEntity> findAll(Pageable pageable);
	
	Page<MatchEntity> findByRoundId(Integer roundId, Pageable pageable);
	
	Optional<MatchEntity> findByDateTime(String dateTime);
	
	@Query("SELECT m FROM MatchEntity m WHERE m.dateTime > :dateTime")
    List<MatchEntity> findAllByDateTimeGreaterThan(@Param("dateTime") String dateTime);
	
	@Query("SELECT m FROM MatchEntity m JOIN m.teams t WHERE t.id = :teamId")
    List<MatchEntity> findByTeamId(@Param("teamId") Integer teamId);
	
}
