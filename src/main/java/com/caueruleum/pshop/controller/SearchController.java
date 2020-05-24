package com.caueruleum.pshop.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caueruleum.pshop.entity.Category;
import com.caueruleum.pshop.entity.Product;
import com.caueruleum.pshop.model.PageModel;
import com.caueruleum.pshop.service.CategoryService;
import com.caueruleum.pshop.service.SearchService;


/**
 * This class is for the search functionality
 * 
 * All the search related requests are processed here (doesn`t need to be a full text(hibernate search) search)
 * 
 * @author caueruleum
 *
 */
@Controller
@RequestMapping("/search")
public class SearchController 
{
	@Autowired
	private SearchService searchService;
	
	@Autowired 
	private CategoryService categoryService;
	
	
	/**
	 * 
	 * This method takes care of the general search cases,
	 * indexing the product`s name, description and tag name.
	 * 
	 * Does a keyword hibernate search query on those three fields.
	 * 
	 * 
	 * @param model the spring mvc model
	 * @param query this is the get parameter passed in the url.
	 * @return String 
	 */
	@GetMapping
	public String showResults(Model model, @RequestParam("query") String query, 
			@RequestParam(name="page", required=false) Integer page) 
	{
		
		if(page == null) 
		{
			page = 1;
		}
		
		PageModel<Product> pageModel =  searchService.findGeneralPaginate(query, page);
		
		model.addAttribute("page", pageModel);
		
		return "products";
	}
	
	/**
	 * Do a basic query (not using hibernate search) to find the given category
	 * and then load all the products from this category in the view
	 * 
	 * @param model spring mvc model
	 * @param id the id of the desired category
	 * @return String
	 */
	@GetMapping("/category/{categoryId}")
	public String showResultsByCategory(Model model, @PathVariable("categoryId") int id) 
	{
		
		Category categ = this.categoryService.findById(id);
		
		model.addAttribute("categ", categ);
		
		return "products";
		
	}
	
}
