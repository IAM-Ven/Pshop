package com.caueruleum.pshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.dao.AuthorityDAO;
import com.caueruleum.pshop.entity.Authority;
import com.caueruleum.pshop.helper.PaginationHelper;
import com.caueruleum.pshop.model.PageModel;

@Service
public class AuthorityServiceImpl implements AuthorityService
{
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@Value("${pshop.defaults.admin.page.size}")
	private int maxPageItems;
	
	@Transactional
	@Override
	public List<Authority> findAll()
	{
		return authorityDAO.findAll();
	}
	
	@Transactional
	@Override
	public PageModel<Authority> findAllPaginate(int page)
	{
		int[] coordinates = PaginationHelper.calculatePaginationCoordinates(page, maxPageItems);
		
		List<Authority> items = authorityDAO.findAllPaginate(coordinates[0], coordinates[1]);
		PageModel<Authority> model =
				new PageModel<Authority>(items, maxPageItems, page);
		
		return model;
		
	}
	
	@Transactional
	@Override
	public Authority findById(Integer id) 
	{
		return authorityDAO.findById(id);
	}
	
}
