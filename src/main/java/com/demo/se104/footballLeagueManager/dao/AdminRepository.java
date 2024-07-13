package com.demo.se104.footballLeagueManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.se104.footballLeagueManager.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

	AdminEntity findByEmail(String email);
	
	AdminEntity findByUserId(String name);
	
	Page<AdminEntity> findAll(Pageable pageable);
}
