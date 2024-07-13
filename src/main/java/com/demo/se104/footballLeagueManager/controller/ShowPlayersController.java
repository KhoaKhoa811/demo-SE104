package com.demo.se104.footballLeagueManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowPlayersController {

	@GetMapping("/players")
	public String showPlayers(Model theModel) {
		
		return "Players";
	}
}
