package com.caueruleum.pshop.admin.service;

import com.caueruleum.pshop.dto.admin.AdminUserDTO;
import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.exception.AuthorityDoesNotExistException;

public interface AdminUserService
{

	public User handleMerge(AdminUserDTO dto) throws AuthorityDoesNotExistException;
	
}
