package com.demo.se104.footballLeagueManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.se104.footballLeagueManager.entity.AdminEntity;
import com.demo.se104.footballLeagueManager.service.AdminService;

@Controller
public class loginController {
	
	private AdminService adminService;
	private JavaMailSender javaMailSender;
	
	@Autowired
	public loginController(AdminService adminService, JavaMailSender javaMailSender) {
		this.adminService = adminService;
		this.javaMailSender = javaMailSender;
	}

	@GetMapping("/login")
	public String showMyLoginPage() {
		
		return "login";
	}
	
	@GetMapping("/password-missing")
	public String showMyPage() {
		return "password-missing";
	}
	
	@PostMapping("/password-missing")
	public String processPassword(@RequestParam("username") String email, Model model) {
		AdminEntity admin = adminService.findByEmail(email);
		
		if(admin == null) {
			model.addAttribute("error", "Không tìm thấy email. Vui lòng nhập lại");
			return "password-missing";
		}
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Gửi lại tài khoản, mật khẩu");
		msg.setText("Tài khoản: " + admin.getUserId() + "\n" + "Mật khẩu: " + admin.getPassword());
		javaMailSender.send(msg);
		
		model.addAttribute("success", "Email đã được gửi. Vui lòng kiểm tra email");
		return "password-missing";
	}
}
