package com.caueruleum.pshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.Authority;
import com.caueruleum.pshop.model.AuthorityType;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO 
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveAuthority(Authority authority) 
	{
		em.merge(authority);
	}
	
	@Override
	public List<Authority> findAll()
	{
		TypedQuery<Authority> query = em.createQuery("FROM Authority", Authority.class);
		
		return query.getResultList();	
	}
	
	@Override
	public Authority findById(int id) 
	{
		return em.find(Authority.class, id);
	}

	@Override
	public List<Authority> findAllPaginate(int offset, int max) 
	{
		TypedQuery<Authority> query = em.createQuery("FROM Authority ORDER BY id", Authority.class);
		
		query.setFirstResult(offset);
		query.setMaxResults(max);
		
		return query.getResultList();
	}
	

	@Override
	public Authority findByType(AuthorityType authorityType) 
	{
		TypedQuery<Authority> query = em.createQuery("FROM Authority WHERE authority = :authority", Authority.class);
		
		query.setParameter("authority", authorityType.getType());
		
		Authority auth = query.getResultList().stream().findFirst().orElse(null);
		
		return auth;
		
	}

	@Override
	public void addAuthority(Authority auth, AuthorityType type) 
	{
		auth.setAuthority(auth.getAuthority() + "," + type.getType());
	}
	

}
