package com.demo.se104.footballLeagueManager.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.demo.se104.footballLeagueManager.convert.MatchConvert;
import com.demo.se104.footballLeagueManager.convert.TeamConvert;
import com.demo.se104.footballLeagueManager.dao.MatchRepository;
import com.demo.se104.footballLeagueManager.entity.MatchEntity;
import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Match;
import com.demo.se104.footballLeagueManager.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

	private MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
    	this.matchRepository = matchRepository;
    }
	
	@Override
	public List<Match> findAll() {
		List<MatchEntity> theMatchEntity = matchRepository.findAll();
		
		List<Match> result = new ArrayList<Match>();
		for(MatchEntity item : theMatchEntity) {
			result.add(MatchConvert.convertEntityToModel(item));
		}
		return result;
	}

	@Override
	public Match findById(Integer theId) {
		Optional<MatchEntity> result = matchRepository.findById(theId);

		Match thePlayer = null;

        if (result.isPresent()) {
        	thePlayer = MatchConvert.convertEntityToModel(result.get());
        }
        else {
            throw new RuntimeException("Did not find admin id - " + theId);
        }

        return thePlayer;
	}

	@Override
	public MatchEntity save(Match theMatch) {
		MatchEntity tempMatch = MatchConvert.convertModelToEntity(theMatch);
		System.out.println(tempMatch.getId());
		return matchRepository.save(tempMatch);
	}

	@Override
	public void deleteById(Integer theId) {
		matchRepository.deleteById(theId);
		
	}

	@Override
	public List<Match> findByRoundId(Integer roundId) {
		List<MatchEntity> theMatchEntity = matchRepository.findByRoundId(roundId);
		
		List<Match> result = new ArrayList<Match>();
		for(MatchEntity item : theMatchEntity) {
			result.add(MatchConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override
	public List<Match> findByDateTime() {
        Pageable pageable = PageRequest.of(0, 5); // Page 0, 5 results per page
        
        List<MatchEntity> theMatchEntity = matchRepository.findAllByOrderByDateTimeDesc(pageable);
		
		List<Match> result = new ArrayList<Match>();
		for(MatchEntity item : theMatchEntity) {
			result.add(MatchConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override

	public Page<Match> findAll(Pageable pageable) {
        // Ensure sorting by dateTime in the Pageable object
        Pageable sortedByDateTime = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by("dateTime").ascending() // or descending() for descending order
        );

        Page<MatchEntity> matchEntityPage = matchRepository.findAll(sortedByDateTime);

        return matchEntityPage.map(matchEntity -> MatchConvert.convertEntityToModel(matchEntity));
    }

	@Override
	public Page<Match> findByRoundId(Integer roundId, Pageable pageable) {
        Pageable sortedByDateTime = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by("dateTime").ascending() // or descending() for descending order
        );

        Page<MatchEntity> matchEntityPage = matchRepository.findByRoundId(roundId, sortedByDateTime);

        return matchEntityPage.map(matchEntity -> MatchConvert.convertEntityToModel(matchEntity));
	}

	@Override
	public Match findByDateTime(String dateTime) {
		Optional<MatchEntity> result = matchRepository.findByDateTime(dateTime);

		Match thePlayer = null;

        if (result.isPresent()) {
        	thePlayer = MatchConvert.convertEntityToModel(result.get());
        }

        return thePlayer;
	}

	@Override
	public List<Match> findMatchByGreaterThanDateTime(String dateTime) {
		List<MatchEntity> theMatchEntity = matchRepository.findAllByDateTimeGreaterThan(dateTime);
		
		List<Match> result = new ArrayList<Match>();
		for(MatchEntity item : theMatchEntity) {
			result.add(MatchConvert.convertEntityToModel(item));
		}
		
		return result;
	}
}