package com.caueruleum.pshop.dao;

import java.util.Date;

import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.entity.VerificationToken;

public interface VerificationTokenDAO 
{
	/**
	 * Save token to the database
	 * @param user the user
	 * @param token the token
	 * @param expDate the date
	 */
	public void saveToken(User user, String token, Date expDate);

	/**
	 * Find token record
	 * @param token the token
	 * @return the full token entity
	 */
	public VerificationToken findToken(String token);

	public VerificationToken findTokenByUserUsername(String username);

	public void removeToken(VerificationToken token);
	

}
