package com.demo.se104.footballLeagueManager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.se104.footballLeagueManager.entity.PlayerEntity;
import com.demo.se104.footballLeagueManager.model.Player;

public interface PlayerService {

	List<Player> findAll();

	Player findById(Integer theId);

	PlayerEntity save(Player thePlayer);

    void deleteById(Integer theId);
    
    List<Player> findByTeamId(Integer id);
    
    Page<Player> findByTeamId(Integer id, Pageable pageable);
    
    Integer countAll();
    
    Integer countByTeamId(Integer teamId);
    
    Integer countByPlayerTypeIdAndTeamId(Integer playerTypeId, Integer teamId);
}
