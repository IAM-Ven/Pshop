package com.caueruleum.pshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.UserDetail;

@Repository
public class UserDetailImpl implements UserDetailDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveUserDetail(UserDetail userDetail) 
	{
		em.merge(userDetail);

	}
	
	@Override
	public UserDetail getUserDetailById(int id) 
	{
		var userDetail = em.find(UserDetail.class, id);
		
		return userDetail;
	}
	
	@Override
	public List<UserDetail> getUserDetails()
	{
		TypedQuery<UserDetail> ud = em.createQuery("FROM UsedDetail", UserDetail.class);
		
		return ud.getResultList();
		
	}
	
}
