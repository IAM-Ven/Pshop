package com.caueruleum.pshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.dao.SearchDAO;
import com.caueruleum.pshop.entity.Category;
import com.caueruleum.pshop.entity.Product;
import com.caueruleum.pshop.helper.PaginationHelper;
import com.caueruleum.pshop.model.PageModel;

/**
 * The search service layer implementation
 * 
 * @author caueruleum
 * @since 2020-02-21
 */

@Service
public class SearchServiceImpl implements SearchService
{
	
	@Autowired
	private SearchDAO searchDAO;
	
	
	@Value("${pshop.defaults.page.size}")
	private int maxPageItems;
	
	@Override
	@Transactional
	public List<Category> findCategoryByName(String name) 
	{
		
		return searchDAO.findCategoryByName(name);
		
	}
	
	@Override
	@Transactional
	public PageModel<Product> findGeneralPaginate(String searchQuery, int page) 
	{
		int[] coordinates = PaginationHelper.calculatePaginationCoordinates(page, maxPageItems);
		
		List<Product> items = searchDAO.findGeneralPaginate(searchQuery, coordinates[0], coordinates[1]);
		
		PageModel<Product> pageModel = new PageModel<Product>(items, maxPageItems, page);
		
		return pageModel;
	}
	
	
}
