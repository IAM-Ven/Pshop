package com.caueruleum.pshop.admin.controller.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caueruleum.pshop.entity.Image;
import com.caueruleum.pshop.service.ImageService;

@Controller
@RequestMapping("/admin/database/image")
public class AdminDatabaseImageController
{
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/")
	private String getAll(Model model) 
	{
		List<Image> images = imageService.findAll();
		
		model.addAttribute("images", images);
		
		return "admin/database/table";
		
		
	}
}
