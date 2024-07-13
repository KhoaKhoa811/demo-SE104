package com.demo.se104.footballLeagueManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.se104.footballLeagueManager.convert.AdminConvert;
import com.demo.se104.footballLeagueManager.convert.PlayerConvert;
import com.demo.se104.footballLeagueManager.dao.PlayerRepository;
import com.demo.se104.footballLeagueManager.entity.AdminEntity;
import com.demo.se104.footballLeagueManager.entity.PlayerEntity;
import com.demo.se104.footballLeagueManager.model.Player;
import com.demo.se104.footballLeagueManager.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository thePlayerRepository) {
    	playerRepository = thePlayerRepository;
    }
	
	@Override
	public List<Player> findAll() {
		List<PlayerEntity> thePlayerEntity = playerRepository.findAll();
		
		List<Player> result = new ArrayList<Player>();
		for(PlayerEntity item : thePlayerEntity) {
			result.add(PlayerConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override
	public Player findById(Integer theId) {
		Optional<PlayerEntity> result = playerRepository.findById(theId);

		Player thePlayer = null;

        if (result.isPresent()) {
        	thePlayer = PlayerConvert.convertEntityToModel(result.get());
        }

        return thePlayer;
	}

	@Override
	public PlayerEntity save(Player thePlayer) {
		PlayerEntity tempPlayer = PlayerConvert.convertModelToEntity(thePlayer);
		System.out.println(tempPlayer.getId());
		return playerRepository.save(tempPlayer);

	}

	@Override
	public void deleteById(Integer theId) {
		playerRepository.deleteById(theId);
	}

	@Override
	public List<Player> findByTeamId(Integer id) {
		List<PlayerEntity> thePlayerEntity = playerRepository.findByTeamId(id);
		
		List<Player> result = new ArrayList<Player>();
		for(PlayerEntity item : thePlayerEntity) {
			result.add(PlayerConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override
	public Page<Player> findByTeamId(Integer id, Pageable pageable) {
		Page<PlayerEntity> playerEntityPage = playerRepository.findByTeamId(id, pageable);
		
        return playerEntityPage.map(playerEntity -> PlayerConvert.convertEntityToModel(playerEntity));
	}

	@Override
	public Integer countAll() {
		return (int)playerRepository.count();
	}

	@Override
	public Integer countByTeamId(Integer teamId) {
		return (int)playerRepository.countByTeamId(teamId);
	}

	@Override
	public Integer countByPlayerTypeIdAndTeamId(Integer playerTypeId, Integer teamId) {
		return (int)playerRepository.countByPlayerTypeIdAndTeamId(playerTypeId, teamId);
	}

}
