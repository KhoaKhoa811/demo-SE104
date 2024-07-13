package com.demo.se104.footballLeagueManager.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.se104.footballLeagueManager.model.Chart;
import com.demo.se104.footballLeagueManager.model.Match;
import com.demo.se104.footballLeagueManager.model.Regulation;
import com.demo.se104.footballLeagueManager.model.Team;
import com.demo.se104.footballLeagueManager.service.ChartService;
import com.demo.se104.footballLeagueManager.service.MatchService;
import com.demo.se104.footballLeagueManager.service.RegulationService;
import com.demo.se104.footballLeagueManager.utils.AssignRankingUtils;
import com.demo.se104.footballLeagueManager.utils.UpdateChartUtils;

@Controller
@RequestMapping("/admin")
public class ChartController {

	private ChartService chartService;
	private MatchService matchService;
	private RegulationService regulationService; 

	public ChartController(ChartService chartService, MatchService matchService, RegulationService regulationService) {
		this.chartService = chartService;
		this.matchService = matchService;
		this.regulationService = regulationService;
	}
	
	@GetMapping("/chart")
	public String listAdmin(@RequestParam(defaultValue = "0") int page, Model theModel) {
		
		page = (page < 0) ? 0 : page;
		
		Page<Chart> charts = chartService.findAll(PageRequest.of(page, 8));
		
		Chart chart = new Chart();
		
		theModel.addAttribute("charts", charts);
		theModel.addAttribute("chart", chart);
		
		return "chart";
	}
	
	@GetMapping("chart/update")
	public String updateChart(Model theModel) {
		
		List<Match> matches = matchService.findAll();
		List<Regulation> regulation = regulationService.findAll();
		List<Chart> chart = chartService.findAll();
		
		List<Chart> updatedChart = UpdateChartUtils.updateChart(matches, regulation, chart);
		
		List<Chart> rankedChart = AssignRankingUtils.assignRankings(updatedChart);
		
		for(Chart item : rankedChart) {
			chartService.save(item);
		}
		
		return "redirect:/admin/chart";
	}
}
