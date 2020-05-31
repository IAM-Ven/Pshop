package com.caueruleum.pshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.caueruleum.pshop.entity.Category;
import com.caueruleum.pshop.service.CategoryService;

/**
 * Handles all the requests for the category specific functionality (/category)
 * 
 * @author caueruleum
 *
 */

@Controller
public class CategoryController 
{
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * Shows all the categories.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/category")
	public String showCategory(Model model)
	{
		
		List<Category> categories = categoryService.findAll();
		
		
		model.addAttribute("categories", categories);
		
		return "category";

	}
	
}
