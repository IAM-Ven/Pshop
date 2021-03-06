package com.caueruleum.pshop.admin.controller.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caueruleum.pshop.admin.service.AdminUserService;
import com.caueruleum.pshop.dto.admin.AdminUserDTO;
import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.model.AuthorityType;
import com.caueruleum.pshop.service.UserService;

@Controller
@RequestMapping("admin/database/user")
public class AdminDatabaseUserController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminUserService adminUserService;
	
	@GetMapping("/")
	public String getUser(Model model) 
	{
		
		List<User> users = userService.findAll();
		
		model.addAttribute("users", users);
		
		return "admin/database/table/user-table";
	
	}
	
	@GetMapping(value = {"/cu/{username}", "/cu/"})
	private String getCuUser(@PathVariable(required=false) String username, Model model ) 
	{
		// If we are passing it in "flash" attribute
		if(model.getAttribute("user") != null) 
		{
			return "admin/database/crud/user-crud";
		}
		
		AdminUserDTO dto;
		
		AuthorityType[] auth = AuthorityType.values();
		model.addAttribute("authorities", auth);
		
		if(username == null) 
		{
			dto = new AdminUserDTO();
			
		}else 
		{
			User user = userService.findUserByUsername(username);
			
			if(user == null) 
			{
				model.addAttribute("error", "doesNotExist");
				return "redirect:/admin/database/user";
			}
			
			dto = new AdminUserDTO(user.getUsername(), user.getPassword(), user.getIsActive(),
					user.getAuthority().getAuthority(), user.getUserDetail().getFirstName(),
					user.getUserDetail().getLastName(), user.getUserDetail().getEmail(),
					user.getUserDetail().getPhoneNumber(), user.getUserDetail().getAddress());
			
		}
		
		model.addAttribute("dto", dto);
		
		return "admin/database/crud/user-crud";
		
	}
	
	@PostMapping("/process-cu")
	private String processCuUser(@ModelAttribute("dto") AdminUserDTO dto, 
			RedirectAttributes attr, Model model) 
	{
		try 
		{
			User user = this.adminUserService.handleMerge(dto);
			
			attr.addAttribute("success", true);
			attr.addFlashAttribute("user", user);
			
			return "redirect:/admin/database/user/";
			
		}
		catch (Exception ex) 
		{
			// TODO: Proper handling (THINK ABOUT HOW TO DO IT ? MAYBE ASPECT ? )
			
			attr.addAttribute("success", false);
			
			return "redirect:/admin/database/user/";
			
		}
	}
	
	@GetMapping("/delete/{username}/")
	private String processDelete(@PathVariable String username, Model model, RedirectAttributes attr) 
	{
		User user = this.userService.findUserByUsername(username);
		
		try 
		{
			this.userService.deleteUser(user);
			attr.addAttribute("success", true);
		}
		catch(Exception ex) 
		{
			System.out.println(ex);
			attr.addAttribute("error", true);
		}
		
		return "redirect:/admin/database/user/";
		
	}
	
}
