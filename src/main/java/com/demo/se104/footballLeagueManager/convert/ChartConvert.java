package com.demo.se104.footballLeagueManager.convert;

import com.demo.se104.footballLeagueManager.entity.ChartEntity;
import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Chart;

public class ChartConvert {

	public static Chart convertEntityToModel(ChartEntity chartEntity) {
		Chart result = new Chart();
		
		result.setId(chartEntity.getId());
		result.setTeamId(chartEntity.getTeam().getId());
		result.setTeamName(chartEntity.getTeam().getName());
		result.setWin(chartEntity.getWin().toString());
		result.setDraw(chartEntity.getDraw().toString());
		result.setLoss(chartEntity.getLoss().toString());
		result.setGoalDifference(chartEntity.getGoalDifference().toString());
		result.setRank(chartEntity.getRank().toString());
		
		return result;
	}
	
	public static ChartEntity convertModelToEntity(Chart chart) {
		ChartEntity result = new ChartEntity();
		
		result.setId(chart.getId());
		result.setWin(Integer.parseInt(chart.getWin()));
		result.setDraw(Integer.parseInt(chart.getDraw()));
		result.setLoss(Integer.parseInt(chart.getLoss()));
		result.setGoalDifference(Integer.parseInt(chart.getGoalDifference()));
		TeamEntity team = new TeamEntity();
		team.setId(chart.getTeamId());
		result.setTeam(team);
		result.setRank(Integer.parseInt(chart.getRank()));
		
		return result;
	}
}
