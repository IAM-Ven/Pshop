package com.caueruleum.pshop.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController
{
	@GetMapping("/")
	private String redirectToHome() 
	{
		return "redirect:/admin/home/";
	}
	
	@GetMapping("/home/")
	private String showHome() 
	{
		return "admin/index";
	}
}
