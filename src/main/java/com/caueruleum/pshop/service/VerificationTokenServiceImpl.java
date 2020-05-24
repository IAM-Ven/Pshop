package com.caueruleum.pshop.service;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.dao.UserDAO;
import com.caueruleum.pshop.dao.VerificationTokenDAO;
import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.entity.VerificationToken;
import com.caueruleum.pshop.exception.TokenExpiredException;
import com.caueruleum.pshop.exception.TokenNotFoundException;
import com.caueruleum.pshop.exception.UserAlreadyActivatedException;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService 
{
	@Value("${pshop.defaults.token.expiration}")
	private int expiryTimeMinutes;
	
	@Autowired
	private VerificationTokenDAO verificationTokenDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	/**
	 * Calculate expiration date
	 * @return calculated expiration date
	 */
	private Date generateDate() 
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryTimeMinutes);
		
		return new Date(cal.getTime().getTime());
	}
	
	
	@Override
	@Transactional
	public void createToken(User user, String token) 
	{
		Date expirationDate = this.generateDate();
		
		verificationTokenDAO.saveToken(user, token, expirationDate);

	}
	
	@Override
	@Transactional
	public VerificationToken findToken(String token) 
	{
		return verificationTokenDAO.findToken(token);
	}
	
	@Override
	@Transactional
	public VerificationToken findTokenByUserUsername(String username) 
	{
		return verificationTokenDAO.findTokenByUserUsername(username);
	}
	
	@Override
	@Transactional
	public void removeToken(VerificationToken token) 
	{
		verificationTokenDAO.removeToken(token);
	}
	
	
	@Transactional
	@Override
	public void confirmToken(String token) throws TokenNotFoundException, TokenExpiredException
	{
		VerificationToken verificationToken = verificationTokenDAO.findToken(token);
		
		if (verificationToken == null) 
		{
			throw new TokenNotFoundException();
		}
		
		Calendar cal = Calendar.getInstance();
		
		if(verificationToken.getExpiration().getTime() - cal.getTime().getTime() <= 0) 
		{
			throw new TokenExpiredException();
		}
		
		User user = verificationToken.getUser();
		user.setActive(true);
		
		// Remove old token.
		verificationTokenDAO.removeToken(verificationToken);
		
		userDAO.saveUser(user);
		
		
	}

	@Transactional
	@Override
	public User removeOldVerificationTokenByUsername(String username) 
			throws TokenNotFoundException, UserAlreadyActivatedException 
	{
		VerificationToken token = 
				verificationTokenDAO.findTokenByUserUsername(username);
		
		if(token == null) 
		{
			throw new TokenNotFoundException();
		}
		
		User user = token.getUser();
		
		if (user.getIsActive()) 
		{
			throw new UserAlreadyActivatedException();
		}
		
		verificationTokenDAO.removeToken(token);
		
		return user;
		
		
	}
	

}
