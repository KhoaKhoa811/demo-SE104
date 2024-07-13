package com.demo.se104.footballLeagueManager.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.se104.footballLeagueManager.dao.ChartRepository;
import com.demo.se104.footballLeagueManager.dao.MatchRepository;
import com.demo.se104.footballLeagueManager.entity.ChartEntity;
import com.demo.se104.footballLeagueManager.entity.MatchEntity;
import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Team;
import com.demo.se104.footballLeagueManager.service.MatchService;
import com.demo.se104.footballLeagueManager.service.TeamService;

@Controller
@RequestMapping("/admin")
public class TeamController {
	
	private TeamService teamService;
	private ChartRepository chartRepository;
	private MatchRepository matchRepository;

	public TeamController(TeamService teamService, ChartRepository chartRepository, MatchRepository matchRepository) {
		this.teamService = teamService;
		this.chartRepository = chartRepository;
		this.matchRepository = matchRepository;
	}

	@GetMapping("/teams")
	public String listTeam(@RequestParam(defaultValue = "0") int page, Model theModel) {
		
		page = (page < 0) ? 0 : page;
		
		Page<Team> theTeams = teamService.findAll(PageRequest.of(page, 5));
		
		Team theTeam = new Team();
		
		// add to the spring model
		theModel.addAttribute("teams", theTeams);
		theModel.addAttribute("team", theTeam);
		return "team";
	}
	
	@PostMapping("/teams")
	public String saveTeam(@RequestParam(defaultValue = "0") int page, @ModelAttribute("team") Team team, Model model) {
	    System.out.println(team.getId());
	    
	    String error = null;
	    TeamEntity nameTeam = teamService.findByName(team.getName());
	    
	    if (team.getId() != null) {
	        Team existingTeam = teamService.findById(team.getId());
	        
	        List<MatchEntity> matches= matchRepository.findByTeamId(team.getId());
			
			if(!matches.isEmpty()) {
				page = (page < 0) ? 0 : page;
				
				Page<Team> theTeams = teamService.findAll(PageRequest.of(page, 5));
				
				Team theTeam = new Team();
				
				// add to the spring model
				model.addAttribute("teams", theTeams);
				model.addAttribute("team", theTeam);
				model.addAttribute("error", "Đội bóng đã được được xếp lịch thi đấu. Vui lòng kiểm tra lại trước khi thay đổi thông tin.");
				return "team";
			}
	        
	        if (nameTeam != null) {
	        	if (existingTeam.getId() != nameTeam.getId()) {
	        		error = "Tên đội bóng đã tồn tại. Vui lòng nhập lại";
			    	model.addAttribute("error", error);
			    	
			    	page = (page < 0) ? 0 : page;
					
					Page<Team> theTeams = teamService.findAll(PageRequest.of(page, 5));
					
					Team theTeam = new Team();
					
					// add to the spring model
					model.addAttribute("teams", theTeams);
					model.addAttribute("team", theTeam);
					return "team";
	        	}
	        }
	        
	        if (existingTeam != null) {
	            existingTeam.setName(team.getName());
	            existingTeam.setHomeGround(team.getHomeGround());
	            teamService.save(existingTeam);
	        }
	    } else {
	    	
	    	if(nameTeam != null) {
		    	error = "Tên đội bóng đã tồn tại. Vui lòng nhập lại";
		    	model.addAttribute("error", error);
		    	
		    	page = (page < 0) ? 0 : page;
				
				Page<Team> theTeams = teamService.findAll(PageRequest.of(page, 5));
				
				Team theTeam = new Team();
				
				// add to the spring model
				model.addAttribute("teams", theTeams);
				model.addAttribute("team", theTeam);
				return "team";
		    }
	    	
	        TeamEntity teamEntity = teamService.save(team);
	        ChartEntity chart = new ChartEntity();
	        chart.setTeam(teamEntity);
	        chartRepository.save(chart);
	    }

	    return "redirect:/admin/teams?page="+page; 
	}
	
	@GetMapping("/teams/delete")
	public String delete(@RequestParam(defaultValue = "0") int page, @RequestParam("teamId") int theId, Model model) {
		
		List<MatchEntity> matches= matchRepository.findByTeamId(theId);
		
		if(!matches.isEmpty()) {
			page = (page < 0) ? 0 : page;
			
			Page<Team> theTeams = teamService.findAll(PageRequest.of(page, 5));
			
			Team theTeam = new Team();
			
			// add to the spring model
			model.addAttribute("teams", theTeams);
			model.addAttribute("team", theTeam);
			model.addAttribute("error", "Đội bóng đã được được xếp lịch thi đấu. Vui lòng kiểm tra lại trước khi thay đổi thông tin.");
			return "team";
		}
		
		teamService.deleteById(theId);
		
		return "redirect:/admin/teams?page="+page;
	}
}
