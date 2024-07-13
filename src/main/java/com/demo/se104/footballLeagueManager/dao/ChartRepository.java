package com.demo.se104.footballLeagueManager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.se104.footballLeagueManager.entity.ChartEntity;

public interface ChartRepository extends JpaRepository<ChartEntity, Integer>{

	Page<ChartEntity> findAllByOrderByGoalDifferenceDesc(Pageable pageable);
	
	List<ChartEntity> findAllByOrderByGoalDifferenceDesc();
}
