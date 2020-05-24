package com.caueruleum.pshop.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caueruleum.pshop.dao.UserDetailDAO;
import com.caueruleum.pshop.entity.UserDetail;

@Service
public class UserDetailServiceImpl implements UserDetailService
{
	@Autowired
	private UserDetailDAO userDetailDAO;
	
	@Transactional
	@Override
	public UserDetail getUserDetailById(int id) 
	{
		return this.userDetailDAO.getUserDetailById(id);
	}
	
	
}
