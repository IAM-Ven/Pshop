package com.caueruleum.pshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.dao.AuthorityDAO;
import com.caueruleum.pshop.dao.UserDAO;
import com.caueruleum.pshop.dto.RegistrationDTO;
import com.caueruleum.pshop.entity.Authority;
import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.entity.UserDetail;
import com.caueruleum.pshop.exception.EmailExistsException;
import com.caueruleum.pshop.exception.PasswordMatchesException;
import com.caueruleum.pshop.exception.PhoneNumberExistsException;
import com.caueruleum.pshop.exception.UsernameExistsException;
import com.caueruleum.pshop.model.AuthorityType;

@Service
public class UserServiceImpl implements UserService 
{
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/**
	 *
	 */
	@Override
	@Transactional
	public User registerUser(RegistrationDTO registrationDTO)
	throws EmailExistsException, PhoneNumberExistsException, UsernameExistsException
	{
		
		// Normalize phone number
		if(registrationDTO.getPhoneNumber().startsWith("0")) 
		{
			String phoneNumberPrefix = "+359";
			String number = new String(phoneNumberPrefix + registrationDTO.getPhoneNumber().substring(1));
			registrationDTO.setPhoneNumber(number);
		}
		
		// Checks
		if(this.usernameExists(registrationDTO.getUsername())) 
		{
			
			throw new UsernameExistsException();
		}
		else if(this.emailExists(registrationDTO.getEmail())) 
		{
			throw new EmailExistsException();
		}
		else if (this.phoneNumberExists(registrationDTO.getPhoneNumber()))
		{
			throw new PhoneNumberExistsException();
		}
		
		UserDetail userDetail = new UserDetail(registrationDTO.getAddress(), registrationDTO.getEmail(), 
				registrationDTO.getPhoneNumber(), registrationDTO.getFirstName(), registrationDTO.getLastName());
		
		User user = new User(registrationDTO.getUsername(), encoder.encode(registrationDTO.getPassword()), false);
		
		Authority authority = new Authority(AuthorityType.USER);
		authority.setUser(user);
		
		user.setAuthority(authority);
		user.setUserDetail(userDetail);
		userDetail.setUser(user);
		
		userDAO.addUser(user);
		
		return user;
	}
	
	@Override
	@Transactional
	public void saveUser(User user) 
	{
		userDAO.saveUser(user);
		
	}
	
	@Override
	@Transactional
	public User findUserByUsername(String username) 
	{	
		return userDAO.findByUsername(username);
	}
	
	private boolean emailExists(String email) 
	{
		User user = userDAO.findByEmail(email);
		
		if (user != null) 
		{
			return true;
		}
		
		return false;
	}
	
	@Transactional
	@Override
	public void changePassword(String newPassword, User user) throws PasswordMatchesException 
	{
		if (encoder.matches(newPassword, user.getPassword())) 
		{
			throw new PasswordMatchesException();
		}
		
		String hashedPassword = encoder.encode(newPassword);
		
		user.setPassword(hashedPassword);
		
		userDAO.saveUser(user);
		
	}
	
	@Transactional
	@Override
	public List<User> findAll() 
	{
		return userDAO.findAll();
	}
	
	
	private boolean usernameExists(String username) 
	{
		User user = userDAO.findByUsername(username);
		
		if (user != null) 
		{
			return true;
		}
		
		return false;
		
	}
	
	
	private boolean phoneNumberExists(String phoneNumber) 
	{
		
		User user = userDAO.findByPhoneNumber(phoneNumber);
		
		if (user != null) 
		{
			return true;
		}
		
		return false;
		
	}

}
