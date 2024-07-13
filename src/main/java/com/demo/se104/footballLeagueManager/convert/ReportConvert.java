package com.demo.se104.footballLeagueManager.convert;

import com.demo.se104.footballLeagueManager.entity.GoalTypeEntity;
import com.demo.se104.footballLeagueManager.entity.MatchEntity;
import com.demo.se104.footballLeagueManager.entity.PlayerEntity;
import com.demo.se104.footballLeagueManager.entity.ReportEntity;
import com.demo.se104.footballLeagueManager.model.Report;

public class ReportConvert {

	public static Report convertEntityToModel(ReportEntity reportEntity) {
		Report result = new Report();
		
		result.setId(reportEntity.getId());
		result.setPlayerId(reportEntity.getPlayer().getId().toString());
		result.setPlayerName(reportEntity.getPlayer().getName());
		result.setTeam(reportEntity.getPlayer().getTeam().getName());
		result.setGoalTypeId(reportEntity.getGoalType().getId().toString());
		result.setGoalTypeName(reportEntity.getGoalType().getName());
		result.setTime(reportEntity.getTime());
		
		return result;
	}
	
	public static ReportEntity convertModelToEntity(Report report) {
		ReportEntity result = new ReportEntity();
		
		result.setId(report.getId());
		result.setTime(report.getTime());
		PlayerEntity player = new PlayerEntity();
		player.setId(Integer.parseInt(report.getPlayerId()));
		result.setPlayer(player);
		GoalTypeEntity goalType = new GoalTypeEntity();
		goalType.setId(Integer.parseInt(report.getGoalTypeId()));
		result.setGoalType(goalType);
		MatchEntity match = new MatchEntity();
		match.setId(report.getMatchId());
		result.setMatch(match);
		
		return result;
	}
}
