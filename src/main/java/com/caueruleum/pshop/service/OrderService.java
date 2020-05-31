package com.caueruleum.pshop.service;

import java.util.List;

import com.caueruleum.pshop.entity.Order;

public interface OrderService
{

	/**
	 * Contact the dao to find all the orders in the database
	 * @return
	 */
	public List<Order> findAll();

}
