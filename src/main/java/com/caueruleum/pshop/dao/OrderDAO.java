package com.caueruleum.pshop.dao;

import java.util.List;

import com.caueruleum.pshop.entity.Order;

public interface OrderDAO
{

	/**
	 * Get all the orders in the database.
	 * @return
	 */
	public List<Order> findAll();

	/**
	 * Find an order by id
	 * @param id the id of the order
	 * @return Order order the order
	 */
	public Order findById(int id);
	
}
