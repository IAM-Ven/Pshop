package com.caueruleum.pshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.dao.CategoryDAO;
import com.caueruleum.pshop.entity.Category;

/**
 * The category service layer implementation
 * 
 * @author caueruleum
 * @since 2020-02-21
 */
@Service
public class CategoryServiceImpl implements CategoryService
{
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	
	@Transactional
	public List<Category> findAll()
	{
		
		return categoryDAO.findAllCategories();
	}

	@Override
	@Transactional
	public Category findById(int id) 
	{
		return categoryDAO.findCategoryById(id);
	}

	

}
