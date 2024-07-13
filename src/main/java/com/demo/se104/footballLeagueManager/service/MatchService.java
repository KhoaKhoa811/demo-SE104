package com.demo.se104.footballLeagueManager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.se104.footballLeagueManager.entity.MatchEntity;
import com.demo.se104.footballLeagueManager.model.Match;

public interface MatchService {

	List<Match> findAll();

	Match findById(Integer theId);

	MatchEntity save(Match theMatch);

    void deleteById(Integer theId);
    
    List<Match> findByRoundId(Integer roundId);
   
    List<Match> findByDateTime();
    
    Page<Match> findAll(Pageable pageable);
    
    Page<Match> findByRoundId(Integer roundId, Pageable pageable);
    
    Match findByDateTime(String dateTime);
    
    List<Match> findMatchByGreaterThanDateTime(String dateTime);
}
