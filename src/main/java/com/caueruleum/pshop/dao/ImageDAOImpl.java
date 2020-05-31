package com.caueruleum.pshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.Image;

@Repository
public class ImageDAOImpl implements ImageDAO
{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Image> findAll()
	{
		TypedQuery<Image> q = em.createQuery("FROM Image", Image.class);
		
		return q.getResultList();
		
	}
	
	@Override
	public Image findImageById(int id) 
	{
		return em.find(Image.class, id);
	}
	
}
