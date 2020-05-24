package com.caueruleum.pshop.service;

import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.entity.VerificationToken;
import com.caueruleum.pshop.exception.TokenExpiredException;
import com.caueruleum.pshop.exception.TokenNotFoundException;
import com.caueruleum.pshop.exception.UserAlreadyActivatedException;

public interface VerificationTokenService 
{
	/**
	 * This method generates the token
	 * @param user the user
	 * @param token the token
	 * @return VerificationToken the record in db
	 */
	public void createToken(User user, String token);

	/**
	 * Calls the dao and asks for the token
	 * @param token the token to find
	 * @return full entity
	 */
	public VerificationToken findToken(String token);
	
	/**
	 * 
	 * @param token
	 * @throws TokenNotFoundException
	 * @throws TokenExpiredException
	 */
	
	/**
	 * This method is responsible for checking 
	 * @param token
	 * @throws TokenNotFoundException
	 * @throws TokenExpiredException
	 */
	public void confirmToken(String token) throws TokenNotFoundException, TokenExpiredException;
	
	/**
	 * This method calls the dao to delete a token from the database
	 * 
	 * @param token VerificationToken the token to delete
	 */
	public void removeToken(VerificationToken token);

	/**
	 * This method call the dao to find a token by a username
	 * 
	 * @param username the username of the user
	 * @return VerificationToken the verification token
	 */
	public VerificationToken findTokenByUserUsername(String username);

	/**
	 * This method removes the old verification token of an user
	 * 
	 * @param username String the username
	 * @return User the user of the token
	 * 
	 * @throws TokenNotFoundException
	 * @throws UserAlreadyActivatedException
	 */
	public User removeOldVerificationTokenByUsername(String username) throws TokenNotFoundException, UserAlreadyActivatedException;

}
