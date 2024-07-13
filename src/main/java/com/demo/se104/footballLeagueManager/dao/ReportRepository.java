package com.demo.se104.footballLeagueManager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.se104.footballLeagueManager.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer>{

	List<ReportEntity> findByMatchId(Integer matchId);
	
	Page<ReportEntity> findByMatchId(Integer matchId, Pageable pageable);
}
