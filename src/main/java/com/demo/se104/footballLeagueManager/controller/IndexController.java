package com.demo.se104.footballLeagueManager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.se104.footballLeagueManager.model.Chart;
import com.demo.se104.footballLeagueManager.model.Match;
import com.demo.se104.footballLeagueManager.model.Report;
import com.demo.se104.footballLeagueManager.service.ChartService;
import com.demo.se104.footballLeagueManager.service.MatchService;
import com.demo.se104.footballLeagueManager.service.ReportService;
import com.demo.se104.footballLeagueManager.utils.NearestFutureMatchUtils;
import com.demo.se104.footballLeagueManager.utils.NearestPastMatchUtils;

@Controller
public class IndexController {
	
	private ChartService chartService;
	private MatchService matchService;
	private ReportService reportService;

	public IndexController(ChartService chartService, MatchService matchService, ReportService reportService) {
		this.chartService = chartService;
		this.matchService = matchService;
		this.reportService = reportService;
	}

	@GetMapping("/")
	public String showHome(Model theModel) {
		List<Chart> charts = chartService.findAllByOrder();
		List<Match> matches = matchService.findAll();
		Match nearestFutureMatch = NearestFutureMatchUtils.findNearestFutureMatch(matches);
		Match nearestPastMatch = NearestPastMatchUtils.findNearestPastMatch(matches);
		
		List<Report> matchReport = null;
		if(nearestPastMatch != null) {
			matchReport = reportService.findByMatchId(nearestPastMatch.getId());
		}
		Chart chart = new Chart();
		
		theModel.addAttribute("charts", charts);
		theModel.addAttribute("chart", chart);
		theModel.addAttribute("nearestFutureMatch", nearestFutureMatch);
		theModel.addAttribute("nearestPastMatch", nearestPastMatch);
		theModel.addAttribute("matchReport", matchReport);
		
		return "index";
	}
}
