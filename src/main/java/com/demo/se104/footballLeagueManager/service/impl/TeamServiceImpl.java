package com.demo.se104.footballLeagueManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.se104.footballLeagueManager.convert.AdminConvert;
import com.demo.se104.footballLeagueManager.convert.TeamConvert;
import com.demo.se104.footballLeagueManager.dao.AdminRepository;
import com.demo.se104.footballLeagueManager.dao.TeamRepository;
import com.demo.se104.footballLeagueManager.entity.AdminEntity;
import com.demo.se104.footballLeagueManager.entity.RoleEntity;
import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Admin;
import com.demo.se104.footballLeagueManager.model.Team;
import com.demo.se104.footballLeagueManager.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository theTeamRepository) {
    	teamRepository = theTeamRepository;
    }
	
	@Override
	public List<Team> findAll() {
		List<TeamEntity> theTeamEntity = teamRepository.findAll();
		
		List<Team> result = new ArrayList<Team>();
		for(TeamEntity item : theTeamEntity) {
			result.add(TeamConvert.convertEntityToModel(item));
		}
		return result;
	}

	@Override
	public Team findById(Integer theId) {
		Optional<TeamEntity> result = teamRepository.findById(theId);

		Team theTeam = null;

        if (result.isPresent()) {
        	theTeam = TeamConvert.convertEntityToModel(result.get());
        }
        else {
            throw new RuntimeException("Did not find admin id - " + theId);
        }

        return theTeam;
	}

	@Override
	public TeamEntity save(Team theTeam) {
		TeamEntity tempTeam = TeamConvert.convertModelToEntity(theTeam);
		System.out.println(tempTeam.getId());
		return teamRepository.save(tempTeam);
	}

	@Override
	public void deleteById(Integer theId) {
		teamRepository.deleteById(theId);
	}

	@Override
	public Integer countAll() {
		return (int) teamRepository.count();
	}

	@Override
	public TeamEntity findByName(String name) {
		return teamRepository.findByName(name);
	}

	@Override
	public Page<Team> findAll(Pageable pageable) {
		Page<TeamEntity> teamEntityPage = teamRepository.findAll(pageable);
		
        return teamEntityPage.map(teamEntity -> TeamConvert.convertEntityToModel(teamEntity));
	}

}
