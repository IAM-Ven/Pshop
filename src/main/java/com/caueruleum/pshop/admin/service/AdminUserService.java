package com.caueruleum.pshop.admin.service;

import com.caueruleum.pshop.dto.admin.AdminUserDTO;
import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.exception.AuthorityDoesNotExistException;

public interface AdminUserService
{

	/**
	 * This is where we decide weather we create a resource, or update an existing one.
	 * @param dto The user input
	 * @return User user the new user
	 * @throws AuthorityDoesNotExistException This is thrown if we give a non existent authority
	 */
	public User handleMerge(AdminUserDTO dto) throws AuthorityDoesNotExistException;
	
}
