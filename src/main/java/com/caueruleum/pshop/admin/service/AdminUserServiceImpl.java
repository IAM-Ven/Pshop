package com.caueruleum.pshop.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.dao.UserDAO;
import com.caueruleum.pshop.dto.admin.AdminUserDTO;
import com.caueruleum.pshop.entity.Authority;
import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.entity.UserDetail;
import com.caueruleum.pshop.exception.AuthorityDoesNotExistException;
import com.caueruleum.pshop.model.AuthorityType;

@Repository
public class AdminUserServiceImpl implements AdminUserService
{
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/**
	 * Handles updating the user, user detail and authority
	 * 
	 * @param dto AdminUserDTO basically the user input
	 * @param user User the user so we won`t have to query a second time.
	 */
	private void handleUpdate(AdminUserDTO dto, User user)
	{
		Authority auth = user.getAuthority();
		UserDetail ud = user.getUserDetail();
		
		// Update the authority fields
		auth.setAuthority(dto.getAuthority());

		// Update the user detail fields
		ud.setAddress(dto.getAddress());
		ud.setEmail(dto.getEmail());
		ud.setFirstName(dto.getFirstName());
		ud.setLastName(dto.getLastName());
		ud.setPhoneNumber(dto.getPhoneNumber());
		
		user.setIsActive(dto.getIsActive());
		
		// If we need to update the password.
		if(!dto.getPassword().isEmpty()) 
		{
			user.setPassword(dto.getPassword());
		}
		
	}
	
	/**
	 * Handles the creation process
	 * @param dto the user input basically
	 * @return User user the created user
	 */
	private User handleCreation(AdminUserDTO dto) 
	{
		Authority auth = new Authority(dto.getAuthority());	
		
		User user = new User(dto.getUsername(), dto.getPassword(), dto.getIsActive());
		
		UserDetail userDetail = new UserDetail(dto.getAddress(), dto.getEmail(), 
				dto.getPhoneNumber(), dto.getFirstName(), dto.getLastName());
		
		user.setAuthority(auth);
		user.setUserDetail(userDetail);
		userDetail.setUser(user);
		auth.setUser(user);
		
		try 
		{
			this.userDAO.addUser(user);
		}
		catch (Exception ex)
		{
			throw ex;
		}
		
		return user;
	}
	
	
	@Transactional
	@Override
	public User handleMerge(AdminUserDTO dto) throws AuthorityDoesNotExistException 
	{	
		String password = dto.getPassword();
		// If the password is not empty it means we have something to encode.
		if(!password.isEmpty()) 
		{			
			// We encode it and put it back into the dto.
			String encodedPassword = this.encoder.encode(dto.getPassword());
			
			dto.setPassword(encodedPassword);
		}
		
		// If the authority is not in the enum
		if(!AuthorityType.exists(dto.getAuthority())) 
		{
			// Throw an exception
			throw new AuthorityDoesNotExistException();
		}
		
		// We search for the resource
		User user = this.userDAO.findByUsername(dto.getUsername());
		
		// If we can`t find the resource we just create one
		if(user == null) 
		{
			user = this.handleCreation(dto);
		}
		// If we found it we just update what we can.
		else 
		{
			this.handleUpdate(dto, user);
		}
				
		return user;
		
	}

}
