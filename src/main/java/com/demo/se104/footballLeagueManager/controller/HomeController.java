package com.demo.se104.footballLeagueManager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.se104.footballLeagueManager.model.Match;
import com.demo.se104.footballLeagueManager.service.MatchService;
import com.demo.se104.footballLeagueManager.service.TeamService;

@Controller
public class HomeController {

	private TeamService teamService;
	private MatchService matchService;
	
	public HomeController(TeamService teamService, MatchService matchService) {
		this.teamService = teamService;
		this.matchService = matchService;
	}
	
	@GetMapping("/admin/home")
	public String showHome(Model theModel) {
		
		Integer teamCount = teamService.countAll();
		List<Match> matches = matchService.findByDateTime();
		
		theModel.addAttribute("teamCount", teamCount);
		theModel.addAttribute("matches", matches);
		
		return "home";
	}
}
