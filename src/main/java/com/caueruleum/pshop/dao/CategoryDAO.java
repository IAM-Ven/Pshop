package com.caueruleum.pshop.dao;

import java.util.List;

import com.caueruleum.pshop.entity.Category;

/**
 * The category DAO layer
 * 
 * @author caueruleum
 *
 */

public interface CategoryDAO 
{
	
	/**
	 * Lists all the categories stored in the database.
	 * 
	 * @return List<Category> list of all the categories
	 */
	public List<Category> findAllCategories();
	
	/**
	 * Find a category by id
	 * 
	 * @param id the id of the category
	 * @return Category the category
	 */
	public Category findCategoryById(int id);
	

}
