package com.caueruleum.pshop.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController 
{
	
	/**
	 * This method takes care of showing the login view.
	 * 
	 * @return String
	 */
	@GetMapping("/login")
	public String showLogin() 
	{
		return "login";
	}
	
	
	
	
}
