package com.demo.se104.footballLeagueManager.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.se104.footballLeagueManager.entity.AdminEntity;
import com.demo.se104.footballLeagueManager.model.Admin;
import com.demo.se104.footballLeagueManager.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/accounts")
	public String listAdmin(@RequestParam(defaultValue = "0") int page, Model theModel) {
		
        page = (page < 0) ? 0 : page;
		
		Page<Admin> theAdmins = adminService.findAll(PageRequest.of(page, 5));
		
		Admin theAdmin = new Admin();
		
		// add to the spring model
		theModel.addAttribute("admins", theAdmins);
		theModel.addAttribute("admin", theAdmin);
		return "admin";
	}
	
	@PostMapping("/accounts")
	public String saveAdmin(@RequestParam(defaultValue = "0") int page, @ModelAttribute("admin") Admin admin, Model model) {
	    System.out.println(admin.getId());
	    
	    String error = null;
	    
	    AdminEntity emailAdmin = adminService.findByEmail(admin.getEmail());
	    AdminEntity nameAdmin = adminService.findByUserId(admin.getUsername());
	    
	    if (admin.getId() != null) {
	        Admin existingAdmin = adminService.findById(admin.getId());
	        
	        if(emailAdmin != null) {
	        	if(existingAdmin.getId() != emailAdmin.getId()) {
	        		error = "Tài khoản hoặc email đã tồn tại. Vui lòng nhập lại";
			    	model.addAttribute("error", error);
			    	
			    	page = (page < 0) ? 0 : page;
					
					Page<Admin> theAdmins = adminService.findAll(PageRequest.of(page, 5));
					
					Admin theAdmin = new Admin();
					
					// add to the spring model
					model.addAttribute("admins", theAdmins);
					model.addAttribute("admin", theAdmin);
					return "admin";
	        	}
	        } 
	        if (nameAdmin != null) {
	        	if (existingAdmin.getId() != nameAdmin.getId()) {
	        		error = "Tài khoản hoặc email đã tồn tại. Vui lòng nhập lại";
			    	model.addAttribute("error", error);
			    	
			    	page = (page < 0) ? 0 : page;
					
					Page<Admin> theAdmins = adminService.findAll(PageRequest.of(page, 5));
					
					Admin theAdmin = new Admin();
					
					// add to the spring model
					model.addAttribute("admins", theAdmins);
					model.addAttribute("admin", theAdmin);
					return "admin";
	        	}
	        }
	        
	        if(existingAdmin == null) {
				page = (page < 0) ? 0 : page;
				
				Page<Admin> theAdmins = adminService.findAll(PageRequest.of(page, 5));
				
				Admin theAdmin = new Admin();
				
				// add to the spring model
				model.addAttribute("admins", theAdmins);
				model.addAttribute("admin", theAdmin);
				model.addAttribute("error", "Tài khoản admin không tồn tại. Vui lòng kiểm tra lại");
				return "admin";
			}
	        
	            existingAdmin.setUsername(admin.getUsername());
	            if (!admin.getPassword().isEmpty()) {
	                existingAdmin.setPassword(admin.getPassword());
	            }
	            existingAdmin.setEmail(admin.getEmail());
	            adminService.save(existingAdmin);

	    } else {
	    	if(emailAdmin != null || nameAdmin != null) {
		    	error = "Tài khoản hoặc email đã tồn tại. Vui lòng nhập lại";
		    	model.addAttribute("error", error);
		    	
		    	page = (page < 0) ? 0 : page;
				
				Page<Admin> theAdmins = adminService.findAll(PageRequest.of(page, 5));
				
				Admin theAdmin = new Admin();
				
				// add to the spring model
				model.addAttribute("admins", theAdmins);
				model.addAttribute("admin", theAdmin);
				return "admin";
		    }
	    	
	        adminService.save(admin);
	    }
	    return "redirect:/admin/accounts?page="+page;
	}
	
	@GetMapping("accounts/delete")
	public String delete(@RequestParam(defaultValue = "0") int page, @RequestParam("adminId") int theId, Model model) {
		
		Admin admin = adminService.findById(theId);
		
		if(admin == null) {
			page = (page < 0) ? 0 : page;
			
			Page<Admin> theAdmins = adminService.findAll(PageRequest.of(page, 5));
			
			Admin theAdmin = new Admin();
			
			// add to the spring model
			model.addAttribute("admins", theAdmins);
			model.addAttribute("admin", theAdmin);
			model.addAttribute("error", "Tài khoản admin không tồn tại. Vui lòng kiểm tra lại");
			return "admin";
		}
		
		adminService.deleteById(theId);
		
		return "redirect:/admin/accounts?page="+page;
	}
}
