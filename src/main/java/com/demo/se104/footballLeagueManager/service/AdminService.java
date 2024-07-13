package com.demo.se104.footballLeagueManager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.se104.footballLeagueManager.entity.AdminEntity;
import com.demo.se104.footballLeagueManager.model.Admin;

public interface AdminService {

	List<Admin> findAll();

	Admin findById(Integer theId);

	AdminEntity save(Admin theAdmin);

    void deleteById(Integer theId);
    
    AdminEntity findByEmail(String email);
    
    AdminEntity findByUserId(String name);
    
    Page<Admin> findAll(Pageable pageable);
}
