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

import com.demo.se104.footballLeagueManager.convert.DateTimeConvert;
import com.demo.se104.footballLeagueManager.model.Admin;
import com.demo.se104.footballLeagueManager.model.Match;
import com.demo.se104.footballLeagueManager.model.Player;
import com.demo.se104.footballLeagueManager.model.Regulation;
import com.demo.se104.footballLeagueManager.model.Report;
import com.demo.se104.footballLeagueManager.model.Team;
import com.demo.se104.footballLeagueManager.service.MatchService;
import com.demo.se104.footballLeagueManager.service.PlayerService;
import com.demo.se104.footballLeagueManager.service.RegulationService;
import com.demo.se104.footballLeagueManager.service.ReportService;
import com.demo.se104.footballLeagueManager.service.TeamService;
import com.demo.se104.footballLeagueManager.utils.PlayerGoalCountUtils;

@Controller
@RequestMapping("/admin")
public class MatchController {

	private TeamService teamService;
	private MatchService matchService;
	private PlayerService playerService;
	private RegulationService regulationService;
	private ReportService reportService;

	public MatchController(TeamService teamService, MatchService matchService, PlayerService playerService, RegulationService regulationService, ReportService reportService) {
		this.teamService = teamService;
		this.matchService = matchService;
		this.playerService = playerService;
		this.regulationService = regulationService;
		this.reportService = reportService;
	}
	
	@GetMapping("/matches")
	public String listTeam(@RequestParam(defaultValue = "0") int page, Model theModel) {
		
		page = (page < 0) ? 0 : page;
		
		Page<Match> theMatches = matchService.findAll(PageRequest.of(page, 5));

		List<Team> theTeams = teamService.findAll();
		
		
		Team theTeam = new Team();
		Match theMatch = new Match();
		
		// add to the spring model
		theModel.addAttribute("teams", theTeams);
		theModel.addAttribute("team", theTeam);
		theModel.addAttribute("matches", theMatches);
		theModel.addAttribute("match", theMatch);
		theModel.addAttribute("thePage", "matches");
		return "match";
	}
	
	@GetMapping("/matches/find")
	public String listMatch(@RequestParam(defaultValue = "0") int page, @RequestParam("roundId") Integer roundId, Model theModel) {
		
		page = (page < 0) ? 0 : page;
		
		Page<Match> theMatches = matchService.findByRoundId(roundId, PageRequest.of(page, 5));
			
		List<Team> theTeams = teamService.findAll();
		
		
		Team theTeam = new Team();
		Match theMatch = new Match();
		
		// add to the spring model
		theModel.addAttribute("teams", theTeams);
		theModel.addAttribute("team", theTeam);
		theModel.addAttribute("matches", theMatches);
		theModel.addAttribute("match", theMatch);
		theModel.addAttribute("thePage", "find_matches");
		theModel.addAttribute("roundId", roundId);
		return "match";
	}
	
	@PostMapping("/matches")
	public String saveMatch(@RequestParam(defaultValue = "0") int page, @ModelAttribute("match") Match match, Model model) {
	    
	    if (match.getId() != null) {
	    	Match existingMatch = matchService.findById(match.getId());
	        if (existingMatch != null) {
	        	
	        	Match matchDateTime = matchService.findByDateTime(DateTimeConvert.convertEntityToModel(match.getDateTime()));
		    	
		    	if(matchDateTime != null) {
		    		page = (page < 0) ? 0 : page;
		    		
		    		Page<Match> theMatches = matchService.findAll(PageRequest.of(page, 5));

		    		List<Team> theTeams = teamService.findAll();
		    		
		    		
		    		Team theTeam = new Team();
		    		Match theMatch = new Match();
		    		
		    		// add to the spring model
		    		model.addAttribute("teams", theTeams);
		    		model.addAttribute("team", theTeam);
		    		model.addAttribute("matches", theMatches);
		    		model.addAttribute("match", theMatch);
		    		model.addAttribute("thePage", "matches");
		    		model.addAttribute("error", "Thời gian thi đấu đã tồn tại. Vui lòng kiểm tra lại");
		    		return "match";
		    	}
	        	
	        	existingMatch.setTeam1Id(match.getTeam1Id());
	        	existingMatch.setTeam2Id(match.getTeam2Id());
	        	existingMatch.setRoundId(match.getRoundId());
	        	existingMatch.setDateTime(match.getDateTime());
	        	existingMatch.setHomeGround(match.getHomeGround());
	        	matchService.save(existingMatch);
	        }
	    } else {
	    	
	    	Match matchDateTime = matchService.findByDateTime(DateTimeConvert.convertEntityToModel(match.getDateTime()));
	    	
	    	if(matchDateTime != null) {
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Match> theMatches = matchService.findAll(PageRequest.of(page, 5));

	    		List<Team> theTeams = teamService.findAll();
	    		
	    		
	    		Team theTeam = new Team();
	    		Match theMatch = new Match();
	    		
	    		// add to the spring model
	    		model.addAttribute("teams", theTeams);
	    		model.addAttribute("team", theTeam);
	    		model.addAttribute("matches", theMatches);
	    		model.addAttribute("match", theMatch);
	    		model.addAttribute("thePage", "matches");
	    		model.addAttribute("error", "Thời gian thi đấu đã tồn tại. Vui lòng kiểm tra lại");
	    		return "match";
	    	}
	    	
	    	List<Regulation> regulation = regulationService.findAll();
	    	
	    	Integer team1Count = playerService.countByTeamId(Integer.parseInt(match.getTeam1Id()));
	    	Integer team2Count = playerService.countByTeamId(Integer.parseInt(match.getTeam2Id()));
	    	
	    	if(team1Count < regulation.get(0).getMinNumber() || team1Count > regulation.get(0).getMaxNumber()) {
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Match> theMatches = matchService.findAll(PageRequest.of(page, 5));

	    		List<Team> theTeams = teamService.findAll();
	    		
	    		
	    		Team theTeam = new Team();
	    		Match theMatch = new Match();
	    		
	    		// add to the spring model
	    		model.addAttribute("teams", theTeams);
	    		model.addAttribute("team", theTeam);
	    		model.addAttribute("matches", theMatches);
	    		model.addAttribute("match", theMatch);
	    		model.addAttribute("thePage", "matches");
	    		model.addAttribute("error", "Số lượng cầu thủ đội 1 không hợp lệ. Vui lòng kiểm tra lại");
	    		return "match";
	    	}
	    	
	    	System.out.println(team2Count);
	    	System.out.println(regulation.get(0).getMinNumber());
	    	System.out.println(regulation.get(0).getMaxNumber());
	    	
	    	if(team2Count < regulation.get(0).getMinNumber() || team2Count > regulation.get(0).getMaxNumber()) {
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Match> theMatches = matchService.findAll(PageRequest.of(page, 5));

	    		List<Team> theTeams = teamService.findAll();
	    		
	    		
	    		Team theTeam = new Team();
	    		Match theMatch = new Match();
	    		
	    		// add to the spring model
	    		model.addAttribute("teams", theTeams);
	    		model.addAttribute("team", theTeam);
	    		model.addAttribute("matches", theMatches);
	    		model.addAttribute("match", theMatch);
	    		model.addAttribute("thePage", "matches");
	    		model.addAttribute("error", "Số lượng cầu thủ đội 2 không hợp lệ. Vui lòng kiểm tra lại");
	    		return "match";
	    	}
	    	
	    	matchService.save(match);
	    }

	    return "redirect:/admin/matches";
	}
	
	@GetMapping("matches/delete")
	public String delete(@RequestParam("matchId") int theId) {
		
		List<Report> reports = reportService.findByMatchId(theId);
		
		for (Report report : reports) {
			Player player = playerService.findById(Integer.parseInt(report.getPlayerId()));
	        Player updatedPlayer = PlayerGoalCountUtils.goalDelete(player, report);
	        playerService.save(updatedPlayer);
		}
		
		matchService.deleteById(theId);
		
		return "redirect:/admin/matches";
	}
	
	
}