package com.caueruleum.pshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caueruleum.pshop.entity.Product;
import com.caueruleum.pshop.model.PageModel;
import com.caueruleum.pshop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController 
{

	@Autowired
	private ProductService productService;
	
	@Value("${pshop.defaults.page.size}")
	private int maxPageItems;
	
	
	@GetMapping("/all")
	public String showProducts(Model model, @RequestParam(required=false, name="page") Integer page) 
	{
		if (page == null) 
		{
			page = 1;
		}
		
		PageModel<Product> pageModel =
				productService.findAllPaginate(page, maxPageItems);
		
		model.addAttribute("page", pageModel);
		
		return "products";
	}
	
	
	
}
