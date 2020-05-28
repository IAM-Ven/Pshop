package com.caueruleum.pshop.dao;

import java.util.List;

import com.caueruleum.pshop.entity.UserDetail;

public interface UserDetailDAO 
{
	
	/**
	 * Save user detail to database
	 * 
	 * @param userDetail the user detail instance
	 */
	public void saveUserDetail(UserDetail userDetail);

	/**
	 * Get a single user by id
	 * @param id the id of the user detail
	 * @return UserDetail the found userDetail
	 */
	public UserDetail getUserDetailById(int id);

	/**
	 * Get all the user details
	 * @return
	 */
	public List<UserDetail> getUserDetails();
}
