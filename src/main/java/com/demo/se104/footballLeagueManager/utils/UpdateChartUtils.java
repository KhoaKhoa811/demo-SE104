package com.demo.se104.footballLeagueManager.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.se104.footballLeagueManager.model.Chart;
import com.demo.se104.footballLeagueManager.model.Match;
import com.demo.se104.footballLeagueManager.model.Regulation;

public class UpdateChartUtils {

	public static List<Chart> updateChart(List<Match> matches, List<Regulation> regulation, List<Chart> charts) {
		
		Map<Integer, Chart> chartMap = new HashMap<>();
        for (Chart chart : charts) {
            chartMap.put(chart.getTeamId(), chart);
        }
        
        for (Map.Entry<Integer, Chart> entry : chartMap.entrySet()) {
            entry.getValue().setWin("0");
            entry.getValue().setDraw("0");
            entry.getValue().setLoss("0");
            entry.getValue().setGoalDifference("0");
        }
        
        Integer winPoint = regulation.get(0).getWinPoint();
        Integer drawPoint = regulation.get(0).getDrawPoint();
        Integer lossPoint = regulation.get(0).getLossPoint();
        
        for(Match match : matches) {
        	if(DateTimeCompareUtils.compareMatchDateTime(match.getDateTime()) == 1) {
	        	Integer team1Id = Integer.parseInt(match.getTeam1Id());
	        	Integer team2Id = Integer.parseInt(match.getTeam2Id());
	        	Integer team1Goal = match.getTeam1Goal();
	        	Integer team2Goal = match.getTeam2Goal();
	        	
	        	if(team1Goal > team2Goal) {
	        		chartMap.get(team1Id).setWin(Integer.toString(Integer.parseInt(chartMap.get(team1Id).getWin())+1));
	        		chartMap.get(team2Id).setLoss(Integer.toString(Integer.parseInt(chartMap.get(team2Id).getLoss())+1));
	        		chartMap.get(team1Id).setGoalDifference(Integer.toString(Integer.parseInt(chartMap.get(team1Id).getGoalDifference()) + winPoint));
	        		chartMap.get(team2Id).setGoalDifference(Integer.toString(Integer.parseInt(chartMap.get(team2Id).getGoalDifference()) + lossPoint));
	        	} else if (team1Goal < team2Goal) {
	        		chartMap.get(team2Id).setWin(Integer.toString(Integer.parseInt(chartMap.get(team2Id).getWin())+1));
	        		chartMap.get(team1Id).setWin(Integer.toString(Integer.parseInt(chartMap.get(team1Id).getLoss())+1));
	        		chartMap.get(team1Id).setGoalDifference(Integer.toString(Integer.parseInt(chartMap.get(team1Id).getGoalDifference()) + lossPoint));
	        		chartMap.get(team2Id).setGoalDifference(Integer.toString(Integer.parseInt(chartMap.get(team2Id).getGoalDifference()) + winPoint));
	        	} else if (team1Goal == team2Goal) {
	        		chartMap.get(team2Id).setDraw(Integer.toString(Integer.parseInt(chartMap.get(team2Id).getDraw()) + 1));
	        		chartMap.get(team1Id).setDraw(Integer.toString(Integer.parseInt(chartMap.get(team1Id).getDraw()) + 1));
	        		chartMap.get(team1Id).setGoalDifference(Integer.toString(Integer.parseInt(chartMap.get(team1Id).getGoalDifference()) + drawPoint));
	        		chartMap.get(team2Id).setGoalDifference(Integer.toString(Integer.parseInt(chartMap.get(team2Id).getGoalDifference()) + drawPoint));
	        	}
        	}
        	
        }
        
        for(Chart chart : charts) {
        	chart = chartMap.get(chart.getTeamId());
        }
		
		return charts;
	}

	
}
