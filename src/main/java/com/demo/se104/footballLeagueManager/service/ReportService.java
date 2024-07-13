package com.demo.se104.footballLeagueManager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.se104.footballLeagueManager.entity.ReportEntity;
import com.demo.se104.footballLeagueManager.model.Report;

public interface ReportService {

	List<Report> findAll();

	Report findById(Integer theId);

	ReportEntity save(Report theReport);

    void deleteById(Integer theId);
    
    List<Report> findByMatchId(Integer matchId);
    
    Page<Report> findByMatchId(Integer matchId, Pageable pageable);
}
