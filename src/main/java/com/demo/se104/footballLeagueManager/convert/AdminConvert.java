package com.demo.se104.footballLeagueManager.convert;

import com.demo.se104.footballLeagueManager.entity.AdminEntity;
import com.demo.se104.footballLeagueManager.model.Admin;

public class AdminConvert {

	public static Admin convertEntityToModel(AdminEntity adminEntity) {
		Admin result = new Admin();
		
		String password = adminEntity.getPassword();
		String modifiedPassword = password.replace("{noop}", "");
		result.setId(adminEntity.getId());
		result.setUsername(adminEntity.getUserId());
		result.setPassword(modifiedPassword);
		result.setEmail(adminEntity.getEmail());
		
		return result;
	}
	
	public static AdminEntity convertModelToEntity(Admin admin) {
		AdminEntity result = new AdminEntity();
		result.setId(admin.getId());
		result.setUserId(admin.getUsername());
		result.setPassword("{noop}" + admin.getPassword());
		result.setEmail(admin.getEmail());
		
		return result;
	}
}
