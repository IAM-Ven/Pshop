package com.caueruleum.pshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.Product;

/**
 * Implementation for the ProductDAO interface
 * 
 * 
 * 
 * @author caueruleum
 * @since 2020-02-21
 */
@Repository
public class ProductDAOImpl implements ProductDAO 
{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Product> findAll() 
	{
		
		TypedQuery<Product> query = em.createQuery("FROM Products", Product.class);
		
		return query.getResultList();
	}

	@Override
	public Product getById(int id) 
	{
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> getRecent(int limit) 
	{	
		TypedQuery<Product> query = em.createQuery("FROM Product ORDER BY date DESC", Product.class);
		
		query.setMaxResults(limit);
		
		return query.getResultList();
		
	}
	
	public List<Product> findAllPaginate(int offset, int max) 
	{
		
		TypedQuery<Product> query = em.createQuery("FROM Product p ORDER BY p.title", Product.class);
		
		query.setFirstResult(offset);
		query.setMaxResults(max);

		
		return query.getResultList();
		
		
	}


}
