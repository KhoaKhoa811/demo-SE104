package com.demo.se104.footballLeagueManager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.se104.footballLeagueManager.model.PlayerSearch;

public interface PlayerSearchService {

	List<PlayerSearch> findAll();
	
	List<PlayerSearch> findByName(String name);
	
	PlayerSearch findById(Integer id);
	
	Page<PlayerSearch> findAll(Pageable pageable);
	
	Page<PlayerSearch> findByName(String name, Pageable pageable);
}
