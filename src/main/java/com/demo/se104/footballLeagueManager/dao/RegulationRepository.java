package com.demo.se104.footballLeagueManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.se104.footballLeagueManager.entity.RegulationEntity;

public interface RegulationRepository extends JpaRepository<RegulationEntity, Integer> {

}
