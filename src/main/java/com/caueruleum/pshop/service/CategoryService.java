package com.caueruleum.pshop.service;

import java.util.List;

import com.caueruleum.pshop.entity.Category;

/**
 * Intefrace for the cateogry service layer
 * 
 * @author caueruleum
 * @since 2020-02-21
 */
public interface CategoryService 
{
	/**
	 * Contacts the DAO layer and asks for all the categories in the databse.
	 * 
	 * @return List<Category> list of all the categories
	 */
	public List<Category> findAll();
	
	/**
	 * Contacts the DAO layer and asks for a category by its id
	 * 
	 * @param id the id of the category
	 * @return Category the category
	 */
	public Category findById(int id);
	
	
}
