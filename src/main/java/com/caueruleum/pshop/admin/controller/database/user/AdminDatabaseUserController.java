package com.caueruleum.pshop.admin.controller.database.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.service.UserService;

@Controller
@RequestMapping("admin/database/")
public class AdminDatabaseUserController
{
	@Autowired
	private UserService userService;
	
	@GetMapping(value= {"user/", "user/{username}"})
	public String getUser(@PathVariable(required=false) String username, Model model) 
	{
		// If we get the / url we just get all the users then return.
		if(username == null) 
		{
			List<User> users = userService.findAll();
			
			model.addAttribute(users);
			
			return "user-table";
		}
		
		// If we have a parameter we just return the user ready for crud operations
		User user = userService.findUserByUsername(username);
		
		if(user == null) 
		{
			//Temporary 
			// TODO: Make it with internationalization.
			model.addAttribute("error","the user does not exits");
		}
		else 
		{
			// We just add it.
			model.addAttribute("user", user);
		}
		
		return "user-crud";
	}
	
	
	
	
}
