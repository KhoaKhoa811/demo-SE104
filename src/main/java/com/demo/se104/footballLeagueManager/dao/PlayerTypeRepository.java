package com.demo.se104.footballLeagueManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.se104.footballLeagueManager.entity.PlayerTypeEntity;

public interface PlayerTypeRepository extends JpaRepository<PlayerTypeEntity, Integer> {

}
