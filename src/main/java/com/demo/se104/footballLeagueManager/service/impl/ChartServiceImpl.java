package com.demo.se104.footballLeagueManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.se104.footballLeagueManager.convert.ChartConvert;
import com.demo.se104.footballLeagueManager.convert.TeamConvert;
import com.demo.se104.footballLeagueManager.dao.ChartRepository;
import com.demo.se104.footballLeagueManager.entity.ChartEntity;
import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Chart;
import com.demo.se104.footballLeagueManager.service.ChartService;

@Service
public class ChartServiceImpl implements ChartService {

	private ChartRepository chartRepository;

    @Autowired
    public ChartServiceImpl(ChartRepository chartRepository) {
    	this.chartRepository = chartRepository;
    }
	
	@Override
	public List<Chart> findAll() {
		List<ChartEntity> theChartEntity = chartRepository.findAll();
		
		List<Chart> result = new ArrayList<Chart>();
		for(ChartEntity item : theChartEntity) {
			result.add(ChartConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override
	public Chart findById(Integer theId) {
		Optional<ChartEntity> result = chartRepository.findById(theId);

		Chart theChart = null;

        if (result.isPresent()) {
        	theChart = ChartConvert.convertEntityToModel(result.get());
        }
        else {
            throw new RuntimeException("Did not find admin id - " + theId);
        }

        return theChart;
	}

	@Override
	public ChartEntity save(Chart theChart) {
		ChartEntity tempMatch = ChartConvert.convertModelToEntity(theChart);
		return chartRepository.save(tempMatch);
	}

	@Override
	public void deleteById(Integer theId) {
		chartRepository.deleteById(theId);
		
	}

	@Override
	public Page<Chart> findAll(Pageable pageable) {
		Page<ChartEntity> chartEntityPage = chartRepository.findAllByOrderByGoalDifferenceDesc(pageable);
		
        return chartEntityPage.map(chartEntity -> ChartConvert.convertEntityToModel(chartEntity));
	}

	@Override
	public List<Chart> findAllByOrder() {
		List<ChartEntity> theChartEntity = chartRepository.findAllByOrderByGoalDifferenceDesc();
		
		List<Chart> result = new ArrayList<Chart>();
		for(ChartEntity item : theChartEntity) {
			result.add(ChartConvert.convertEntityToModel(item));
		}
		
		return result;
	}

}
