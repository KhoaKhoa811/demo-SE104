package com.demo.se104.footballLeagueManager.convert;

import java.util.ArrayList;
import java.util.List;

import com.demo.se104.footballLeagueManager.entity.MatchEntity;
import com.demo.se104.footballLeagueManager.entity.RoundEntity;
import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Match;

public class MatchConvert {

	public static Match convertEntityToModel(MatchEntity matchEntity) {
		Match result = new Match();

		result.setId(matchEntity.getId());
		result.setTeam1Id(matchEntity.getTeams().get(0).getId().toString());
		result.setTeam1Name(matchEntity.getTeams().get(0).getName());
		result.setTeam2Id(matchEntity.getTeams().get(1).getId().toString());
		result.setTeam2Name(matchEntity.getTeams().get(1).getName());
		result.setDateTime(DateTimeConvert.convertEntityToModel(matchEntity.getDateTime()));
		result.setHomeGround(matchEntity.getHomeGround());
		result.setRoundId(matchEntity.getRound().getId().toString());
		result.setRoundName(matchEntity.getRound().getName());
		result.setTeam1Goal(matchEntity.getTeam1Goal());
		result.setTeam2Goal(matchEntity.getTeam2Goal());
		
		return result;
	}
	
	public static MatchEntity convertModelToEntity(Match match) {
		MatchEntity result = new MatchEntity();
		
		result.setId(match.getId());
		TeamEntity team1 = new TeamEntity();
		team1.setId(Integer.parseInt(match.getTeam1Id()));
		TeamEntity team2 = new TeamEntity();
		team2.setId(Integer.parseInt(match.getTeam2Id()));
		List<TeamEntity> teams = new ArrayList<TeamEntity>();
		teams.add(team1);
		teams.add(team2);
		result.setTeams(teams);
		result.setDateTime(DateTimeConvert.convertModelToEntity(match.getDateTime()));
		result.setHomeGround(match.getHomeGround());
		RoundEntity round = new RoundEntity();
		round.setId(Integer.parseInt(match.getRoundId()));
		result.setRound(round);
		result.setTeam1Goal(match.getTeam1Goal() == null ? 0 : match.getTeam1Goal());
		result.setTeam2Goal(match.getTeam2Goal() == null ? 0 : match.getTeam2Goal());
		
		return result;
	}
}
