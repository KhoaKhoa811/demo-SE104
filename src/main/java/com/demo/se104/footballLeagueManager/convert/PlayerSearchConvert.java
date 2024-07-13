package com.demo.se104.footballLeagueManager.convert;

import com.demo.se104.footballLeagueManager.entity.PlayerEntity;
import com.demo.se104.footballLeagueManager.model.PlayerSearch;

public class PlayerSearchConvert {

	public static PlayerSearch convertEntityToModel(PlayerEntity playerEntity) {
		PlayerSearch result = new PlayerSearch();

		result.setId(playerEntity.getId());
		result.setName(playerEntity.getName());
		result.setTeam(playerEntity.getTeam().getName());
		result.setPosition(playerEntity.getPosition());;
		result.setPlayerType(playerEntity.getPlayerType().getName());
		result.setGoal(playerEntity.getGoal());
		
		return result;
	}
}
