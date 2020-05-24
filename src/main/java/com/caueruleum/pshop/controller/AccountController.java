package com.caueruleum.pshop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caueruleum.pshop.dao.UserDAO;
import com.caueruleum.pshop.entity.User;

@Controller
@RequestMapping("/account")
public class AccountController 
{
	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping
	public String showAccount(Model model, Principal principal) 
	{
		String username = principal.getName();
		
		User user = userDAO.findByUsername(username);
		
		model.addAttribute("user", user);
		 	
		
		return "userProfile";
		
	}
}
