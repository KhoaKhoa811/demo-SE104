package com.demo.se104.footballLeagueManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.se104.footballLeagueManager.convert.DateTimeConvert;
import com.demo.se104.footballLeagueManager.model.Match;
import com.demo.se104.footballLeagueManager.model.Report;
import com.demo.se104.footballLeagueManager.service.MatchService;
import com.demo.se104.footballLeagueManager.service.ReportService;
import com.demo.se104.footballLeagueManager.utils.NearestFutureMatchUtils;
import com.demo.se104.footballLeagueManager.utils.NearestPastMatchUtils;

@Controller
public class ShowMatchesController {
	
	private MatchService matchService;
	private ReportService reportService;
	
	@Autowired
	public ShowMatchesController(MatchService matchService, ReportService reportService) {
		super();
		this.matchService = matchService;
		this.reportService = reportService;
	}



	@GetMapping("/matches")
	public String showMatches(Model theModel) {
		
		List<Match> matches = matchService.findAll();
		Match nearestFutureMatch = NearestFutureMatchUtils.findNearestFutureMatch(matches);
		Match nearestPastMatch = NearestPastMatchUtils.findNearestPastMatch(matches);
		
		List<Report> matchReport = null;
		List<Match> upcomingMatches = null;
		
		if(nearestPastMatch != null) {
			matchReport = reportService.findByMatchId(nearestPastMatch.getId());
			upcomingMatches = matchService.findMatchByGreaterThanDateTime(DateTimeConvert.convertEntityToModel(nearestFutureMatch.getDateTime()));
		}
		
		
		theModel.addAttribute("nearestFutureMatch", nearestFutureMatch);
		theModel.addAttribute("nearestPastMatch", nearestPastMatch);
		theModel.addAttribute("matchReport", matchReport);
		theModel.addAttribute("upcomingMatches", upcomingMatches);
		
		return "matches";
	}
}
