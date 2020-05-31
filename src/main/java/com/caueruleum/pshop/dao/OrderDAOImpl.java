package com.caueruleum.pshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.Order;

@Repository
public class OrderDAOImpl implements OrderDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Order> findAll()
	{
		TypedQuery<Order> query = em.createQuery("FROM Order", Order.class);
		
		return query.getResultList();
	}
	
	@Override
	public Order findById(int id) 
	{
		return em.find(Order.class, id);
	}
	
}
