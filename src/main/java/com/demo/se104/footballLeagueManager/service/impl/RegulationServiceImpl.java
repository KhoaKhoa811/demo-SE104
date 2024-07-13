package com.demo.se104.footballLeagueManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.se104.footballLeagueManager.convert.RegulationConvert;
import com.demo.se104.footballLeagueManager.dao.RegulationRepository;
import com.demo.se104.footballLeagueManager.entity.RegulationEntity;
import com.demo.se104.footballLeagueManager.model.Regulation;
import com.demo.se104.footballLeagueManager.service.RegulationService;

@Service
public class RegulationServiceImpl implements RegulationService {

	private RegulationRepository regulationRepository;

    @Autowired
    public RegulationServiceImpl(RegulationRepository regulationRepository) {
    	this.regulationRepository = regulationRepository;
    }
	
	@Override
	public List<Regulation> findAll() {
		List<RegulationEntity> theRegulationEntity = regulationRepository.findAll();
		
		List<Regulation> result = new ArrayList<Regulation>();
		for(RegulationEntity item : theRegulationEntity) {
			result.add(RegulationConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override
	public Regulation findById(Integer id) {
		Optional<RegulationEntity> result = regulationRepository.findById(id);

		Regulation theRegulation = null;

        if (result.isPresent()) {
        	theRegulation = RegulationConvert.convertEntityToModel(result.get());
        }
        else {
            throw new RuntimeException("Did not find admin id - " + id);
        }

        return theRegulation;
	}

	@Override
	public RegulationEntity save(Regulation theRegulation) {
		RegulationEntity tempRegulation = RegulationConvert.convertModelToEntity(theRegulation);
		return regulationRepository.save(tempRegulation);
	}

	
}
