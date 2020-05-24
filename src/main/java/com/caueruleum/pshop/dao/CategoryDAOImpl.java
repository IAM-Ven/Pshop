package com.caueruleum.pshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.Category;

/**
 * Implementation of the CategoryDAO interface
 * 
 * 
 * @author caueruleum
 * @since 2020-02-21
 */

@Repository
public class CategoryDAOImpl implements CategoryDAO 
{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Category> findAllCategories() 
	{
		
		TypedQuery<Category> query = em.createQuery("FROM Category", Category.class);
		
		return query.getResultList();
		
	}

	@Override
	public Category findCategoryById(int id) {
		
		return em.find(Category.class, id);
		
	}

}
