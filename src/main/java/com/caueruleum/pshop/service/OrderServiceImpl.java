package com.caueruleum.pshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caueruleum.pshop.dao.OrderDAO;
import com.caueruleum.pshop.entity.Order;

@Service
public class OrderServiceImpl implements OrderService
{
	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public List<Order> findAll()
	{
		return orderDAO.findAll();
	}
	
}
