package com.demo.se104.footballLeagueManager.convert;

import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Team;

public class TeamConvert {

	public static Team convertEntityToModel(TeamEntity teamEntity) {
		Team result = new Team();

		result.setId(teamEntity.getId());
		result.setName(teamEntity.getName());
		result.setHomeGround(teamEntity.getHomeGround());
		
		return result;
	}
	
	public static TeamEntity convertModelToEntity(Team team) {
		TeamEntity result = new TeamEntity();
		result.setId(team.getId());
		result.setName(team.getName());
		result.setHomeGround(team.getHomeGround());
		return result;
	}
}
