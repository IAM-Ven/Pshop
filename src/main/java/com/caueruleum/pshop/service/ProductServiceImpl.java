package com.caueruleum.pshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.dao.ProductDAO;
import com.caueruleum.pshop.entity.Product;
import com.caueruleum.pshop.helper.PaginationHelper;
import com.caueruleum.pshop.model.PageModel;

/**
 * The product service layer implementation
 * 
 * @author caueruleum
 * @since 2020-02-21
 */
@Service
public class ProductServiceImpl implements ProductService
{	
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	@Transactional
	public List<Product> findAll() 
	{
		return productDAO.findAll();
	}

	@Override
	@Transactional
	public Product findById(int id) 
	{
		return productDAO.getById(id);
	}

	@Override
	@Transactional
	public List<Product> findRecent(int limit) 
	{
		return productDAO.getRecent(limit);
	}

	@Override
	@Transactional
	public PageModel<Product> findAllPaginate(int page, int maxPageItems) 
	{
		
		int[] coordinates = PaginationHelper.calculatePaginationCoordinates(page, maxPageItems);
		
		List<Product> items = productDAO.findAllPaginate(coordinates[0], coordinates[1]);
		
		PageModel<Product> pageModel = new PageModel<Product>(items, maxPageItems, page);
		
		return pageModel;
		
	}
}
