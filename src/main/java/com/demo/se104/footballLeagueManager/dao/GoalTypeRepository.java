package com.demo.se104.footballLeagueManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.se104.footballLeagueManager.entity.GoalTypeEntity;

public interface GoalTypeRepository extends JpaRepository<GoalTypeEntity, Integer>{

}
