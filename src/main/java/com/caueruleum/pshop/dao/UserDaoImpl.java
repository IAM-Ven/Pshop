package com.caueruleum.pshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.User;

@Repository
public class UserDaoImpl implements UserDAO
{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User findByUsername(String username) 
	{
		return em.find(User.class, username);
	}

	@Override
	public List<User> findAll() 
	{
		TypedQuery<User> query = em.createQuery("FROM User", User.class);
		return query.getResultList();
	}

	@Override
	public void saveUser(User user) 
	{
		em.merge(user);
	}

	@Override
	public User findByEmail(String email) 
	{
		
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u JOIN u.userDetail ud WHERE ud.email = :email", User.class);
		
		query.setParameter("email", email);
		
		User user;
		
		try 
		{
		
			user = query.getSingleResult();
		}
		catch(NoResultException ex) 
		{
			user = null;
		}
		
		return user;
	}

	@Override
	public User findByPhoneNumber(String phoneNumber) 
	{
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u JOIN u.userDetail ud WHERE ud.phoneNumber = :phone", User.class);
		
		query.setParameter("phone", phoneNumber);
		
		
		User user;
		
		try {
		
			user =  query.getSingleResult();
		
		}catch(NoResultException ex) 
		{
			user = null;
		}
		
		return user;
		
	}
	
	public User findByUnique(String email, String phoneNumber, String username) 
	{	
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u INNER JOIN u.userDetail ud WHERE "
				+ "u.username = :username OR "
				+ "ud.phoneNumber = :phone OR "
				+ "ud.email = :email", 
				User.class);
		
		query.setParameter("email", email);
		query.setParameter("phone", phoneNumber);
		query.setParameter("username", username);
		
		
		return query.getResultList().stream().findFirst().orElse(null);
		
	}
	
}
