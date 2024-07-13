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

import com.demo.se104.footballLeagueManager.model.Match;
import com.demo.se104.footballLeagueManager.model.Player;
import com.demo.se104.footballLeagueManager.model.PlayerSearch;
import com.demo.se104.footballLeagueManager.model.Regulation;
import com.demo.se104.footballLeagueManager.model.Report;
import com.demo.se104.footballLeagueManager.model.Team;
import com.demo.se104.footballLeagueManager.service.MatchService;
import com.demo.se104.footballLeagueManager.service.PlayerSearchService;
import com.demo.se104.footballLeagueManager.service.PlayerService;
import com.demo.se104.footballLeagueManager.service.RegulationService;
import com.demo.se104.footballLeagueManager.service.ReportService;
import com.demo.se104.footballLeagueManager.service.TeamService;
import com.demo.se104.footballLeagueManager.utils.DateTimeCompareUtils;
import com.demo.se104.footballLeagueManager.utils.PlayerGoalCountUtils;
import com.demo.se104.footballLeagueManager.utils.ReportGoalCountUtils;

@Controller
@RequestMapping("/admin/matches")
public class MatchReportController {
	
	private MatchService matchService;
	private ReportService reportService;
	private PlayerService playerService;
	private RegulationService regulationService;
	private TeamService teamService;

	@Autowired
	public MatchReportController(MatchService matchService, ReportService reportService, PlayerService playerService, RegulationService regulationService, TeamService teamService) {
		this.matchService = matchService;
		this.reportService = reportService;
		this.playerService = playerService;
		this.regulationService = regulationService;
		this.teamService = teamService;
	}

	@GetMapping("/results")
	public String listTeam(@RequestParam(defaultValue = "0") int page, @RequestParam("matchId") Integer matchId, Model theModel) {
		
		Match theMatch = matchService.findById(matchId);
		
		if(DateTimeCompareUtils.compareMatchDateTime(theMatch.getDateTime()) == 0 || DateTimeCompareUtils.compareMatchDateTime(theMatch.getDateTime()) == -1) {
			page = (page < 0) ? 0 : page;
			
			Page<Match> theMatches = matchService.findAll(PageRequest.of(page, 5));

			List<Team> theTeams = teamService.findAll();
			
			
			Team theTeam = new Team();
			Match match = new Match();
			
			// add to the spring model
			theModel.addAttribute("teams", theTeams);
			theModel.addAttribute("team", theTeam);
			theModel.addAttribute("matches", theMatches);
			theModel.addAttribute("match", match);
			theModel.addAttribute("thePage", "matches");
			theModel.addAttribute("error", "Trận đấu vẫn chưa diễn ra. Vui lòng kiểm tra lại.");
			return "match";
		}
		
		
		List<Player> playerTeam1 =  playerService.findByTeamId(Integer.parseInt(theMatch.getTeam1Id()));
		List<Player> playerTeam2 = playerService.findByTeamId(Integer.parseInt(theMatch.getTeam2Id()));
		
		page = (page < 0) ? 0 : page;
		
		Page<Report> reports = reportService.findByMatchId(matchId, PageRequest.of(page, 4));
		
		Report report = new Report();
		
		theModel.addAttribute("match", theMatch);
		theModel.addAttribute("reports", reports);
		theModel.addAttribute("report", report);
		theModel.addAttribute("playerTeam1", playerTeam1);
		theModel.addAttribute("playerTeam2", playerTeam2);
		theModel.addAttribute("matchId", matchId);
		
		return "match-report";
	}
	
	@PostMapping("/results")
	public String saveTeam(@RequestParam(defaultValue = "0") int page, @RequestParam("matchId") int theId, @ModelAttribute("report") Report report, Model model) {
	    
	    if (report.getId() != null) {
	    	Report existingReport = reportService.findById(report.getId());
	    	Report exReport = reportService.findById(report.getId());
	        if (existingReport != null) {
	        	existingReport.setPlayerId(report.getPlayerId());
	        	existingReport.setGoalTypeId(report.getGoalTypeId());
	        	existingReport.setTime(report.getTime());
	        	existingReport.setMatchId(theId);
	        }

			List<Regulation> regulations = regulationService.findAll();
	    	
	    	if(DateTimeCompareUtils.compareTime(regulations.get(0).getMaxScoreTime(), report.getTime()) == 0) {
	    		Match theMatch = matchService.findById(theId);
	    		List<Player> playerTeam1 =  playerService.findByTeamId(Integer.parseInt(theMatch.getTeam1Id()));
	    		List<Player> playerTeam2 = playerService.findByTeamId(Integer.parseInt(theMatch.getTeam2Id()));
	    		
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Report> reports = reportService.findByMatchId(theId, PageRequest.of(page, 4));
	    		
	    		Report newReport = new Report();
	    		
	    		model.addAttribute("match", theMatch);
	    		model.addAttribute("reports", reports);
	    		model.addAttribute("report", newReport);
	    		model.addAttribute("playerTeam1", playerTeam1);
	    		model.addAttribute("playerTeam2", playerTeam2);
	    		model.addAttribute("matchId", theId);
	    		model.addAttribute("error", "Thời gian ghi bàn không hợp lệ. Vui lòng kiểm tra lại");
	    		
	    		return "match-report";
	    	}
	        
	        Match existingMatch = matchService.findById(theId);
	        Match deleteMatch = ReportGoalCountUtils.goalDelete(existingMatch, exReport);
	        Match updatedMatch = ReportGoalCountUtils.goalCount(deleteMatch, existingReport);
	        matchService.save(updatedMatch);
	        reportService.save(existingReport);
	        
	        Player player = playerService.findById(Integer.parseInt(exReport.getPlayerId()));
	        Player deletePlayer = PlayerGoalCountUtils.goalDelete(player, exReport);
			playerService.save(player);

			player = playerService.findById(Integer.parseInt(existingReport.getPlayerId()));
	        Player updatedPlayer = PlayerGoalCountUtils.goalCount(player, existingReport);
	        playerService.save(player);
	    } 
		else {
	    	
	    	List<Regulation> regulations = regulationService.findAll();
	    	
	    	if(DateTimeCompareUtils.compareTime(regulations.get(0).getMaxScoreTime(), report.getTime()) == 0) {
	    		Match theMatch = matchService.findById(theId);
	    		List<Player> playerTeam1 =  playerService.findByTeamId(Integer.parseInt(theMatch.getTeam1Id()));
	    		List<Player> playerTeam2 = playerService.findByTeamId(Integer.parseInt(theMatch.getTeam2Id()));
	    		
	    		page = (page < 0) ? 0 : page;
	    		
	    		Page<Report> reports = reportService.findByMatchId(theId, PageRequest.of(page, 4));
	    		
	    		Report newReport = new Report();
	    		
	    		model.addAttribute("match", theMatch);
	    		model.addAttribute("reports", reports);
	    		model.addAttribute("report", newReport);
	    		model.addAttribute("playerTeam1", playerTeam1);
	    		model.addAttribute("playerTeam2", playerTeam2);
	    		model.addAttribute("matchId", theId);
	    		model.addAttribute("error", "Thời gian ghi bàn không hợp lệ. Vui lòng kiểm tra lại");
	    		
	    		return "match-report";
	    	}
	    	
	    	report.setMatchId(theId);
	    	Match existingMatch = matchService.findById(theId);
	        Match updatedMatch = ReportGoalCountUtils.goalCount(existingMatch, report);
	        matchService.save(updatedMatch);
	        reportService.save(report);
	        
	        Player player = playerService.findById(Integer.parseInt(report.getPlayerId()));
	        Player updatedPlayer = PlayerGoalCountUtils.goalCount(player, report);
	        playerService.save(updatedPlayer);
	    }

	    return "redirect:/admin/matches/results?matchId=" + theId;	
	}
	
	@GetMapping("/results/delete")
	public String delete(@RequestParam("reportId") int theId, @RequestParam("matchId") int matchId) {
		
		Match existingMatch = matchService.findById(matchId);
		Report existingReport = reportService.findById(theId);
		Match updatedMatch = ReportGoalCountUtils.goalDelete(existingMatch, existingReport);
		matchService.save(updatedMatch);
		reportService.deleteById(theId);
		
		Player player = playerService.findById(Integer.parseInt(existingReport.getPlayerId()));
        Player updatedPlayer = PlayerGoalCountUtils.goalDelete(player, existingReport);
        playerService.save(updatedPlayer);
		
		return "redirect:/admin/matches/results?matchId=" + matchId;
	}
}
