package com.caueruleum.pshop.service;

import java.util.List;

import com.caueruleum.pshop.entity.Category;
import com.caueruleum.pshop.entity.Product;
import com.caueruleum.pshop.model.PageModel;

/**
 * The search service layer
 * 
 * @author caueruleum
 * @since 2020-02-21
 */
public interface SearchService 
{
	/**
	 * Contacts the DAO layer and asks for a search for a category by its name.
	 * 
	 * @param name the name of the category.
	 * @return List<Cateogry> list of the found categories.
	 */
	public List<Category> findCategoryByName(String name);
	
	/**
	 * Contacts the DAO layer and asks for a general search from a given query(keywords)
	 * and paginates it
	 * 
	 * @param searchQuery the query(keywords) to perform on
	 * @param page the current page
	 * @return PageModel<Product> the page
	 */
	public PageModel<Product> findGeneralPaginate(String searchQuery, int page);
	
}
