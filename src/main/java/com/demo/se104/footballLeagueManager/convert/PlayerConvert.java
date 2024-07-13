package com.demo.se104.footballLeagueManager.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.demo.se104.footballLeagueManager.entity.PlayerEntity;
import com.demo.se104.footballLeagueManager.entity.PlayerTypeEntity;
import com.demo.se104.footballLeagueManager.entity.TeamEntity;
import com.demo.se104.footballLeagueManager.model.Player;

public class PlayerConvert {

	public static Player convertEntityToModel(PlayerEntity playerEntity) {
		Player result = new Player();

		result.setId(playerEntity.getId());
		result.setName(playerEntity.getName());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = dateFormat.format(playerEntity.getBirthday());
		result.setBirthday(formattedDate);
		result.setPosition(playerEntity.getPosition());
		result.setPlayerType(playerEntity.getPlayerType().getName());
		result.setPlayerTypeId(playerEntity.getPlayerType().getId());
		result.setTeamName(playerEntity.getTeam().getName());
		result.setGoal(playerEntity.getGoal());
		result.setTeamId(playerEntity.getTeam().getId());
		return result;
	}
	
	public static PlayerEntity convertModelToEntity(Player player) {
		PlayerEntity result = new PlayerEntity();
		
		result.setId(player.getId());
		result.setName(player.getName());
		String inputDate = player.getBirthday();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
		try {
			date = dateFormat.parse(inputDate);
			result.setBirthday(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setPosition(player.getPosition());
		PlayerTypeEntity playerType = new PlayerTypeEntity();
		if (player.getPlayerType().equals("Trong nước")) {
			playerType.setId(1);
		} else if (player.getPlayerType().equals("Ngoài nước")) {
			playerType.setId(2);
		} else {
			playerType.setId(Integer.parseInt(player.getPlayerType()));
		}
		result.setPlayerType(playerType);
		TeamEntity team = new TeamEntity();
		team.setId(player.getTeamId());
		result.setTeam(team);
		result.setGoal(player.getGoal() == null ? 0 : player.getGoal());
		
		return result;
	}
}
