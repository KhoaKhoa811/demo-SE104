package com.demo.se104.footballLeagueManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.se104.footballLeagueManager.convert.AdminConvert;
import com.demo.se104.footballLeagueManager.dao.AdminRepository;
import com.demo.se104.footballLeagueManager.entity.AdminEntity;
import com.demo.se104.footballLeagueManager.entity.RoleEntity;
import com.demo.se104.footballLeagueManager.model.Admin;
import com.demo.se104.footballLeagueManager.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository theAdminRepository) {
    	adminRepository = theAdminRepository;
    }

	@Override
	public List<Admin> findAll() {
		List<AdminEntity> theAdminEntity = adminRepository.findAll();
		
		List<Admin> result = new ArrayList<Admin>();
		for(AdminEntity item : theAdminEntity) {
			result.add(AdminConvert.convertEntityToModel(item));
		}
		
		return result;
	}

	@Override
	public Admin findById(Integer theId) {
		Optional<AdminEntity> result = adminRepository.findById(theId);

		Admin theAdmin = null;

        if (result.isPresent()) {
        	theAdmin = AdminConvert.convertEntityToModel(result.get());
        }
        else {
            throw new RuntimeException("Did not find admin id - " + theId);
        }

        return theAdmin;
	}

	@Override
	public AdminEntity save(Admin theAdmin) {		
		AdminEntity tempAdmin = AdminConvert.convertModelToEntity(theAdmin);
		System.out.println(tempAdmin.getId());
		RoleEntity role = new RoleEntity(tempAdmin.getId(), tempAdmin.getUserId(), "ROLE_ADMIN");
		tempAdmin.setRole(role);
		role.setAdmin(tempAdmin);
		return adminRepository.save(tempAdmin);
	}

	@Override
	public void deleteById(Integer theId) {
		adminRepository.deleteById(theId);
	}

	@Override
	public AdminEntity findByEmail(String email) {
		AdminEntity result = adminRepository.findByEmail(email);

        return result;
	}

	@Override
	public AdminEntity findByUserId(String name) {
		AdminEntity result = adminRepository.findByUserId(name);

        return result;
	}

	@Override
	public Page<Admin> findAll(Pageable pageable) {
		Page<AdminEntity> adminEntityPage = adminRepository.findAll(pageable);
		
        return adminEntityPage.map(adminEntity -> AdminConvert.convertEntityToModel(adminEntity));
	}

}
