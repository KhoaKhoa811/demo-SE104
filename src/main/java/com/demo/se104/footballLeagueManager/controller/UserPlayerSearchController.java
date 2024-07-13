package com.demo.se104.footballLeagueManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.se104.footballLeagueManager.model.PlayerSearch;
import com.demo.se104.footballLeagueManager.service.PlayerSearchService;

@Controller
public class UserPlayerSearchController {

    private final PlayerSearchService playerSearchService;

    @Autowired
    public UserPlayerSearchController(PlayerSearchService playerSearchService) {
        this.playerSearchService = playerSearchService;
    }

    @GetMapping("/userPlayersSearch")
    public String listPlayer(@RequestParam(defaultValue = "0") int page, Model theModel) {
        page = (page < 0) ? 0 : page;

        Page<PlayerSearch> thePlayers = playerSearchService.findAll(PageRequest.of(page, 5));

        PlayerSearch thePlayer = new PlayerSearch();

        // add to the spring model
        theModel.addAttribute("players", thePlayers);
        theModel.addAttribute("player", thePlayer);
        theModel.addAttribute("thePage", "players");
        return "userPlayersSearch";
    }

	@GetMapping("/userPlayersSearch/find")
	public String findPlayer(@RequestParam(defaultValue = "0") int page, @RequestParam("query") String query, Model theModel) {
		
		page = (page < 0) ? 0 : page;
		
		Page<PlayerSearch> thePlayers = playerSearchService.findByName(query, PageRequest.of(page, 5));
		
		PlayerSearch thePlayer = new PlayerSearch();
		
		// add to the spring model
		theModel.addAttribute("players", thePlayers);
		theModel.addAttribute("player", thePlayer);
		theModel.addAttribute("query", query);
		theModel.addAttribute("thePage", "players_find");
		return "userPlayersSearch";
	}
}
