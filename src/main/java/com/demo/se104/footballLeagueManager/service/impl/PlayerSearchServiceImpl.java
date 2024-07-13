package com.demo.se104.footballLeagueManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.se104.footballLeagueManager.convert.PlayerConvert;
import com.demo.se104.footballLeagueManager.convert.PlayerSearchConvert;
import com.demo.se104.footballLeagueManager.convert.TeamConvert;
import com.demo.se104.footballLeagueManager.dao.PlayerRepository;
import com.demo.se104.footballLeagueManager.entity.PlayerEntity;
import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Player;
import com.demo.se104.footballLeagueManager.model.PlayerSearch;
import com.demo.se104.footballLeagueManager.service.PlayerSearchService;

@Service
public class PlayerSearchServiceImpl implements PlayerSearchService {
	
	private PlayerRepository playerRepository;

    @Autowired
    public PlayerSearchServiceImpl(PlayerRepository thePlayerRepository) {
    	playerRepository = thePlayerRepository;
    }

	@Override
	public List<PlayerSearch> findAll() {
		List<PlayerEntity> thePlayerEntity = playerRepository.findAll();
		
		List<PlayerSearch> result = new ArrayList<PlayerSearch>();
		for(PlayerEntity item : thePlayerEntity) {
			result.add(PlayerSearchConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override
	public List<PlayerSearch> findByName(String name) {
		List<PlayerEntity> thePlayerEntity = playerRepository.findByNameContainingIgnoreCase(name);
		
		List<PlayerSearch> result = new ArrayList<PlayerSearch>();
		for(PlayerEntity item : thePlayerEntity) {
			result.add(PlayerSearchConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override
	public PlayerSearch findById(Integer id) {
		Optional<PlayerEntity> result = playerRepository.findById(id);

		PlayerSearch thePlayer = null;

        if (result.isPresent()) {
        	thePlayer = PlayerSearchConvert.convertEntityToModel(result.get());
        }
        else {
            throw new RuntimeException("Did not find admin id - " + id);
        }

        return thePlayer;
	}

	@Override
	public Page<PlayerSearch> findAll(Pageable pageable) {
		Page<PlayerEntity> playerEntityPage = playerRepository.findAll(pageable);
		
        return playerEntityPage.map(playerEntity -> PlayerSearchConvert.convertEntityToModel(playerEntity));
	}

	@Override
	public Page<PlayerSearch> findByName(String name, Pageable pageable) {
		Page<PlayerEntity> playerEntityPage = playerRepository.findByNameContainingIgnoreCase(name, pageable);
		
        return playerEntityPage.map(playerEntity -> PlayerSearchConvert.convertEntityToModel(playerEntity));
	}

	
}
