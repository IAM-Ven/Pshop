package com.caueruleum.pshop.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.dao.PasswordResetTokenDAO;
import com.caueruleum.pshop.entity.PasswordResetToken;
import com.caueruleum.pshop.entity.User;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService
{
	
	@Autowired
	private PasswordResetTokenDAO passwordResetTokenDAO;
	
	
	@Value("${pshop.defaults.token.expiration}")
	private int expiryTimeMinutes;
	
	private Date generateDate() 
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryTimeMinutes);
		
		return new Date(cal.getTime().getTime());
	}
	
	@Transactional
	@Override
	public void createToken(User user, String token) 
	{
		PasswordResetToken newToken = new PasswordResetToken();
		
		newToken.setExpiration(generateDate());
		newToken.setToken(token);
		newToken.setUser(user);
		
		
		passwordResetTokenDAO.saveToken(newToken);
		
	}
	
	@Transactional
	@Override
	public PasswordResetToken findTokenByUsername(String username) 
	{
		return passwordResetTokenDAO.findTokenByUsername(username);
	}
	
	@Transactional
	@Override
	public PasswordResetToken findToken(String token)
	{
		return passwordResetTokenDAO.findToken(token);
	}

	@Transactional
	@Override
	public void removeToken(PasswordResetToken token) 
	{
		passwordResetTokenDAO.removeToken(token);
		
	}
	
	

}
