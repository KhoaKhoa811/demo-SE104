package com.demo.se104.footballLeagueManager.utils;

import java.util.Collections;
import java.util.List;

import com.demo.se104.footballLeagueManager.model.Chart;

public class AssignRankingUtils {

	public static List<Chart> assignRankings(List<Chart> teams) {
        if (teams == null || teams.isEmpty()) {
            return teams;
        }
        
     // Sort teams by goal difference in descending order
        Collections.sort(teams, (t1, t2) -> Integer.parseInt(t2.getGoalDifference()) - Integer.parseInt(t1.getGoalDifference()));

        Integer rank = 1;
        int currentRank = 1;
        int previousGoalDifference = Integer.parseInt(teams.get(0).getGoalDifference());

        for (int i = 0; i < teams.size(); i++) {
        	Chart team = teams.get(i);
            int goalDifference = Integer.parseInt(team.getGoalDifference());

            if (goalDifference < previousGoalDifference) {
                rank = currentRank;
            }

            team.setRank(rank.toString());
            currentRank++;

            // Check for teams with the same goal difference
            if (i < teams.size() - 1 && Integer.parseInt(teams.get(i + 1).getGoalDifference()) == goalDifference) {
                // If the next team has the same goal difference, keep the same rank
            } else {
                // If the next team has a different goal difference, increment the rank
                rank = currentRank;
            }

            previousGoalDifference = goalDifference;
        }
        
        return teams;
    }
}
