package com.demo.se104.footballLeagueManager.utils;

import com.demo.se104.footballLeagueManager.model.Player;
import com.demo.se104.footballLeagueManager.model.Report;

public class PlayerGoalCountUtils {

	public static Player goalCount(Player player, Report report) {
		if(report.getGoalTypeId().equals("1") || report.getGoalTypeId().equals("2")) {
			player.setGoal(player.getGoal() + 1);
		}
		return player;
	}
	
	public static Player goalDelete(Player player, Report report) {
		if(report.getGoalTypeId().equals("1") || report.getGoalTypeId().equals("2")) {
			player.setGoal(player.getGoal() - 1);
		}
		return player;
	}

}
