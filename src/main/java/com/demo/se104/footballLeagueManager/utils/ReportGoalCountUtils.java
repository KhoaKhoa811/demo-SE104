package com.demo.se104.footballLeagueManager.utils;

import com.demo.se104.footballLeagueManager.model.Match;
import com.demo.se104.footballLeagueManager.model.Report;

public class ReportGoalCountUtils {

	public static Match goalCount(Match theMatch, Report theReport) {
		
		if(theReport.getTeam().equals(theMatch.getTeam1Name())) {
			if(theReport.getGoalTypeId().equals("1") || theReport.getGoalTypeId().equals("2")) {
				theMatch.setTeam1Goal(theMatch.getTeam1Goal() + 1);
			} 
			else if (theReport.getGoalTypeId().equals("3")) {
				theMatch.setTeam2Goal(theMatch.getTeam2Goal() + 1);
			}
		} 
		else if(theReport.getTeam().equals(theMatch.getTeam2Name())) {
			if(theReport.getGoalTypeId().equals("1") || theReport.getGoalTypeId().equals("2")) {
				theMatch.setTeam2Goal(theMatch.getTeam2Goal() + 1);
			} 
			else if (theReport.getGoalTypeId().equals("3")) {
				theMatch.setTeam1Goal(theMatch.getTeam1Goal() + 1);
			}
		} 
		else {
			System.out.println("khong duoc roi");
		}
		
		return theMatch;
	}
	
	public static Match goalDelete(Match theMatch, Report theReport) {
		
		if(theReport.getTeam().equals(theMatch.getTeam1Name())) {
			if(theReport.getGoalTypeId().equals("1") || theReport.getGoalTypeId().equals("2")) {
				theMatch.setTeam1Goal(theMatch.getTeam1Goal() - 1);
			} else if (theReport.getGoalTypeId().equals("3")) {
				theMatch.setTeam2Goal(theMatch.getTeam2Goal() - 1);
			}
		} else if(theReport.getTeam().equals(theMatch.getTeam2Name())) {
			if(theReport.getGoalTypeId().equals("1") || theReport.getGoalTypeId().equals("2")) {
				theMatch.setTeam2Goal(theMatch.getTeam2Goal() - 1);
			} else if (theReport.getGoalTypeId().equals("3")) {
				theMatch.setTeam1Goal(theMatch.getTeam1Goal() - 1);
			}
		} else {
			System.out.println("khong duoc roi");
		}
		
			
		return theMatch;
	}
}
