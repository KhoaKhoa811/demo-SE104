package com.demo.se104.footballLeagueManager.convert;

import com.demo.se104.footballLeagueManager.entity.RegulationEntity;
import com.demo.se104.footballLeagueManager.model.Regulation;

public class RegulationConvert {

	public static Regulation convertEntityToModel(RegulationEntity regulationEntity) {
		Regulation result = new Regulation();
		
		result.setId(regulationEntity.getId());
		result.setMinAge(regulationEntity.getMinAge());
		result.setMaxAge(regulationEntity.getMaxAge());
		result.setMinNumber(regulationEntity.getMinNumber());
		result.setMaxNumber(regulationEntity.getMaxNumber());
		result.setMaxForeignNumber(regulationEntity.getMaxForeignNumber());
		result.setNumberOfGoalType(regulationEntity.getNumberOfGoalType());
        result.setMaxScoreTime(regulationEntity.getMaxScoreTime());
		result.setWinPoint(regulationEntity.getWinPoint());
		result.setDrawPoint(regulationEntity.getDrawPoint());
		result.setLossPoint(regulationEntity.getLossPoint());
		result.setRankingOrder(regulationEntity.getRankingOrder());
		
		return result;
	}
	
	public static RegulationEntity convertModelToEntity(Regulation regulation) {
		RegulationEntity result = new RegulationEntity();
		
		result.setId(regulation.getId());
		result.setMinAge(regulation.getMinAge());
		result.setMaxAge(regulation.getMaxAge());
		result.setMinNumber(regulation.getMinNumber());
		result.setMaxNumber(regulation.getMaxNumber());
		result.setMaxForeignNumber(regulation.getMaxForeignNumber());
		result.setNumberOfGoalType(regulation.getNumberOfGoalType());
        result.setMaxScoreTime(regulation.getMaxScoreTime());
		result.setWinPoint(regulation.getWinPoint());
		result.setDrawPoint(regulation.getDrawPoint());
		result.setLossPoint(regulation.getLossPoint());
		result.setRankingOrder(regulation.getRankingOrder());
		
		return result;
	}
}
