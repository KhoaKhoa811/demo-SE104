package com.demo.se104.footballLeagueManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.se104.footballLeagueManager.model.Admin;
import com.demo.se104.footballLeagueManager.model.Player;
import com.demo.se104.footballLeagueManager.model.Regulation;
import com.demo.se104.footballLeagueManager.model.Team;
import com.demo.se104.footballLeagueManager.service.PlayerService;
import com.demo.se104.footballLeagueManager.service.RegulationService;
import com.demo.se104.footballLeagueManager.service.TeamService;
import com.demo.se104.footballLeagueManager.utils.DateTimeCompareUtils;

@Controller
@RequestMapping("/admin")
public class PlayerController {

	private PlayerService playerService;
	private TeamService teamService;
	private RegulationService regulationService;
	
	@Autowired
	public PlayerController(PlayerService playerService, TeamService teamService, RegulationService regulationService) {
		this.playerService = playerService;
		this.teamService = teamService;
		this.regulationService = regulationService;
	}
	
	@GetMapping("/players")
	public String listPlayer(@RequestParam(defaultValue = "0") int page, @RequestParam("teamId") int theId, Model theModel) {
		
		page = (page < 0) ? 0 : page;
		
		Page<Player> thePlayers = playerService.findByTeamId(theId, PageRequest.of(page, 5));
		
		Team theTeam = teamService.findById(theId);
		
		Player thePlayer = new Player();
		
		// add to the spring model
		theModel.addAttribute("players", thePlayers);
		theModel.addAttribute("player", thePlayer);
		theModel.addAttribute("team", theTeam);
		theModel.addAttribute("teamId", theId);
		return "player";
	}
	
	@PostMapping("/players")
	public String saveTeam(@RequestParam(defaultValue = "0") int page, @RequestParam("teamId") int theId, @ModelAttribute("player") Player player, Model model) {
	    System.out.println(player.getId());
	    
	    if (player.getId() != null) {
	    	
	    	List<Regulation> regulations = regulationService.findAll();
	    	
	    	Player existingPlayer = playerService.findById(player.getId());
	    	
	    	Integer foreignCount = playerService.countByPlayerTypeIdAndTeamId(2, theId);
	    	
	    	if(existingPlayer.getPlayerTypeId() == 2) {
	    		foreignCount--;
	    	}
	    	
	    	if(foreignCount >= regulations.get(0).getMaxForeignNumber()) {
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Player> thePlayers = playerService.findByTeamId(theId, PageRequest.of(page, 5));
	    		
	    		Team theTeam = teamService.findById(theId);
	    		
	    		Player thePlayer = new Player();
	    		
	    		// add to the spring model
	    		model.addAttribute("players", thePlayers);
	    		model.addAttribute("player", thePlayer);
	    		model.addAttribute("team", theTeam);
	    		model.addAttribute("teamId", theId);
	    		model.addAttribute("error", "Số lượng cầu thủ nước ngoài không hợp lệ. Vui lòng kiểm tra lại");
	    		return "player";
	    	}
	    	
	    	if((DateTimeCompareUtils.calculateAge(player.getBirthday()) < regulations.get(0).getMinAge()) || (DateTimeCompareUtils.calculateAge(player.getBirthday()) > regulations.get(0).getMaxAge())) {
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Player> thePlayers = playerService.findByTeamId(theId, PageRequest.of(page, 5));
	    		
	    		Team theTeam = teamService.findById(theId);
	    		
	    		Player thePlayer = new Player();
	    		
	    		// add to the spring model
	    		model.addAttribute("players", thePlayers);
	    		model.addAttribute("player", thePlayer);
	    		model.addAttribute("team", theTeam);
	    		model.addAttribute("teamId", theId);
	    		model.addAttribute("error", "Tuổi cầu thủ không hợp lệ. Vui lòng nhập lại");
	    		return "player";
	    	}
	    	
	        if (existingPlayer != null) {
	            existingPlayer.setName(player.getName());
	            existingPlayer.setBirthday(player.getBirthday());
	            existingPlayer.setPlayerType(player.getPlayerType());
	            existingPlayer.setPosition(player.getPosition());
	            existingPlayer.setTeamId(theId);
	            playerService.save(existingPlayer);
	        }
	    } else {
	    	
	    	List<Regulation> regulations = regulationService.findAll();
	    	
	    	if(playerService.countByTeamId(theId) >= regulations.get(0).getMaxNumber()) {
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Player> thePlayers = playerService.findByTeamId(theId, PageRequest.of(page, 5));
	    		
	    		Team theTeam = teamService.findById(theId);
	    		
	    		Player thePlayer = new Player();
	    		
	    		// add to the spring model
	    		model.addAttribute("players", thePlayers);
	    		model.addAttribute("player", thePlayer);
	    		model.addAttribute("team", theTeam);
	    		model.addAttribute("teamId", theId);
	    		model.addAttribute("error", "Số lượng cầu thủ không hợp lệ. Vui lòng kiểm tra lại");
	    		return "player";
	    	}
	    	
	    	Integer foreignCount = playerService.countByPlayerTypeIdAndTeamId(2, theId);
	    	
	    	if(player.getPlayerType().equals("1")) {
	    		foreignCount--;
	    	}
	    	
	    	if(foreignCount >= regulations.get(0).getMaxForeignNumber()) {
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Player> thePlayers = playerService.findByTeamId(theId, PageRequest.of(page, 5));
	    		
	    		Team theTeam = teamService.findById(theId);
	    		
	    		Player thePlayer = new Player();
	    		
	    		// add to the spring model
	    		model.addAttribute("players", thePlayers);
	    		model.addAttribute("player", thePlayer);
	    		model.addAttribute("team", theTeam);
	    		model.addAttribute("teamId", theId);
	    		model.addAttribute("error", "Số lượng cầu thủ nước ngoài không hợp lệ. Vui lòng kiểm tra lại");
	    		return "player";
	    	}
	    	
	    	if((DateTimeCompareUtils.calculateAge(player.getBirthday()) < regulations.get(0).getMinAge()) || (DateTimeCompareUtils.calculateAge(player.getBirthday()) > regulations.get(0).getMaxAge())) {
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Player> thePlayers = playerService.findByTeamId(theId, PageRequest.of(page, 5));
	    		
	    		Team theTeam = teamService.findById(theId);
	    		
	    		Player thePlayer = new Player();
	    		
	    		// add to the spring model
	    		model.addAttribute("players", thePlayers);
	    		model.addAttribute("player", thePlayer);
	    		model.addAttribute("team", theTeam);
	    		model.addAttribute("teamId", theId);
	    		model.addAttribute("error", "Tuổi cầu thủ không hợp lệ. Vui lòng nhập lại");
	    		return "player";
	    	}
	    	
	    	player.setTeamId(theId);
	        playerService.save(player);
	    }

	    return "redirect:/admin/players?page=" + page + "&teamId=" + theId;	
	}
	
	@GetMapping("/players/delete")
	public String delete(@RequestParam("playerId") int theId, @RequestParam("teamId") int teamId) {
		
		playerService.deleteById(theId);
		
		return "redirect:/admin/players?teamId=" + teamId;
	}
}
