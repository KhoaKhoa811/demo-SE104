package com.demo.se104.footballLeagueManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.se104.footballLeagueManager.convert.ReportConvert;
import com.demo.se104.footballLeagueManager.dao.ReportRepository;
import com.demo.se104.footballLeagueManager.entity.ReportEntity;
import com.demo.se104.footballLeagueManager.model.Report;
import com.demo.se104.footballLeagueManager.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	private ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
    	this.reportRepository = reportRepository;
    }
	
	@Override
	public List<Report> findAll() {
		List<ReportEntity> theReportEntity = reportRepository.findAll();
		
		List<Report> result = new ArrayList<Report>();
		for(ReportEntity item : theReportEntity) {
			result.add(ReportConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override
	public Report findById(Integer theId) {
		Optional<ReportEntity> result = reportRepository.findById(theId);

		Report theReport = null;

        if (result.isPresent()) {
        	theReport = ReportConvert.convertEntityToModel(result.get());
        }
        else {
            throw new RuntimeException("Did not find admin id - " + theId);
        }

        return theReport;
	}

	@Override
	public ReportEntity save(Report theReport) {
		ReportEntity tempReport = ReportConvert.convertModelToEntity(theReport);
		return reportRepository.save(tempReport);
	}

	@Override
	public void deleteById(Integer theId) {
		reportRepository.deleteById(theId);
		
	}

	@Override
	public List<Report> findByMatchId(Integer matchId) {
		List<ReportEntity> theReportEntity = reportRepository.findByMatchId(matchId);
		
		List<Report> result = new ArrayList<Report>();
		for(ReportEntity item : theReportEntity) {
			result.add(ReportConvert.convertEntityToModel(item));
		}
		Collections.sort(result, new Comparator<Report>() {
            @Override
            public int compare(Report r1, Report r2) {
                return r1.getTime().compareTo(r2.getTime());
            }
        });
		return result;
	}

	@Override
	public Page<Report> findByMatchId(Integer matchId, Pageable pageable) {
		Page<ReportEntity> reportEntityPage = reportRepository.findByMatchId(matchId, pageable);
		
        return reportEntityPage.map(reportEntity -> ReportConvert.convertEntityToModel(reportEntity));
	}

	
}
