package com.caueruleum.pshop.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.entity.VerificationToken;

@Repository
public class VerificationTokenDAOImpl implements VerificationTokenDAO 
{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveToken(User user, String token, Date expDate) 
	{
		
		VerificationToken verificationToken = new VerificationToken(token, expDate);
		verificationToken.setUser(user);
		
		em.persist(verificationToken);
		
	}
	
	@Override
	public VerificationToken findToken(String token) 
	{
		TypedQuery<VerificationToken> query = 
				em.createQuery("FROM VerificationToken vt WHERE vt.token = :token", VerificationToken.class);
		
		query.setParameter("token", token);
		
		return query.getResultList().stream().findFirst().orElse(null);
		
	}
	
	@Override
	public VerificationToken findTokenByUserUsername(String username)
	{
		TypedQuery<VerificationToken> query = 
				em.createQuery("SELECT t "
						+ "FROM VerificationToken t "
						+ "JOIN t.user u "
						+ "WHERE u.username = :username", 
						VerificationToken.class);
		query.setParameter("username", username);
		
		return query.getResultList().stream().findFirst().orElse(null);
		
	}
	
	@Override
	public void removeToken(VerificationToken token) 
	{
		em.remove(token);
	}
	
}
