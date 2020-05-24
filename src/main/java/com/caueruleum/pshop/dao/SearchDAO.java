package com.caueruleum.pshop.dao;

import java.util.List;

import com.caueruleum.pshop.entity.Category;
import com.caueruleum.pshop.entity.Product;

/**
 * 
 * The search DAO layer.
 * 
 * All the database layer interactions for the search functionality are in this class.
 * 
 * @author caueruleum
 * @since 2020-02-21
 */
public interface SearchDAO 
{
	/**
	 * Do a keyword full text search on the categories by name and return a list of found results
	 * with pagination.
	 * 
	 * @param name The name of the category.
	 * @return List<Category> a list of the found categories
	 */
	public List<Category> findCategoryByName(String name);
	
	/**
	 * Do a keyword full text search on the products table by name, description and category name.
	 * Persist them.
	 * 
	 * This is the most general search for products. It performs the search on all the indexed fields.
	 * 
	 * @param searchQuery the keywords to search for
	 * @param offset where to start
	 * @param limit where to end
	 * @return List<Product> a list of the found products.
	 */
	public List<Product> findGeneralPaginate(String searchQuery, int offset, int limit);
}
