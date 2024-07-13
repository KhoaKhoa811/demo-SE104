package com.demo.se104.footballLeagueManager.service;

import java.util.List;

import com.demo.se104.footballLeagueManager.entity.RegulationEntity;
import com.demo.se104.footballLeagueManager.model.Regulation;

public interface RegulationService {

	List<Regulation> findAll();
	Regulation findById(Integer id);
	RegulationEntity save(Regulation theRegulation);
	
}
