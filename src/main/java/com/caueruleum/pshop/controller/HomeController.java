package com.caueruleum.pshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.caueruleum.pshop.entity.Product;
import com.caueruleum.pshop.service.ProductService;

/**
 * This controller is responsible for handling all the requests to the homepage (/)
 * 
 * @author caueruleum
 *
 */
@Controller
public class HomeController 
{
	
	@Autowired
	private ProductService productService;
	
	// This is from the properties file.
	// It is basically a limit for the hql query for retrieving the most recent products.
	@Value("${pshop.defaults.recent.limit}")
	private int defaultLimit;
	
	
	/**
	 * This method is used for handling the requests for the home page.
	 * 
	 * Gets the most ${defaultLimit} ex 5 newest products added to the shop
	 * 
	 * TODO: Gets the most popular products.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String showHome(Model model) 
	{
		List<Product> recent = productService.findRecent(defaultLimit);
		
		model.addAttribute("recent", recent);
		
		// TODO: Add most popular functionality
		
		return "index";
	}
	

}
