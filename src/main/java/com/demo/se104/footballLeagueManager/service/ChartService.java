package com.demo.se104.footballLeagueManager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.se104.footballLeagueManager.entity.ChartEntity;
import com.demo.se104.footballLeagueManager.model.Chart;

public interface ChartService {

	List<Chart> findAll();

	Chart findById(Integer theId);

	ChartEntity save(Chart theChart);

    void deleteById(Integer theId);
    
    Page<Chart> findAll(Pageable pageable);
    
    List<Chart> findAllByOrder();
}
