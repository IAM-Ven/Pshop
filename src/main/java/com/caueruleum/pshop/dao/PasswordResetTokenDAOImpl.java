package com.caueruleum.pshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.PasswordResetToken;

@Repository
public class PasswordResetTokenDAOImpl implements PasswordResetTokenDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveToken(PasswordResetToken token) 
	{
		em.merge(token);
	}
	
	@Override
	public void removeToken(PasswordResetToken token) 
	{
		em.remove(token);
	}
	
	@Override
	public PasswordResetToken findTokenByUsername(String username) 
	{
		TypedQuery<PasswordResetToken> query = 
				em.createQuery("SELECT t FROM PasswordResetToken t JOIN t.user u WHERE u.username = :username",PasswordResetToken.class); 
	
		query.setParameter("username", username);
		
		return query.getResultList().stream().findFirst().orElse(null);
		
	}
	
	@Override
	public PasswordResetToken findToken(String token) 
	{
		TypedQuery<PasswordResetToken> query = 
				em.createQuery("FROM PasswordResetToken t WHERE t.token = :token", PasswordResetToken.class);
		query.setParameter("token", token);
		
		return query.getResultList().stream().findFirst().orElse(null);
	}
	
	
}
