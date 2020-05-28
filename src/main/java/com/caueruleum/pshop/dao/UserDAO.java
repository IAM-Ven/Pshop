package com.caueruleum.pshop.dao;

import java.util.List;

import com.caueruleum.pshop.entity.User;

/**
 * The user DAO layer
 * 
 * @author caueruleum
 * @since 2020-02-21
 */
public interface UserDAO 
{
	/**
	 * Save a user to the database.
	 * 
	 * @param user a fully set user.
	 */
	public void saveUser(User user);
	
	/**
	 * Find a user by its username.
	 * 
	 * @param username the username of the user.
	 * @return User the found user.
	 */
	public User findByUsername(String username);
	
	/**
	 * Find all the users stored in the database.
	 * 
	 * @return List<User> the users.
	 */
	public List<User> findAll();
	
	/**
	 *  Find a user by email
	 *  
	 * @param email the email
	 * @return User the user
	 */
	public User findByEmail(String email);
	
	/**
	 * Find a user by phone number
	 * 
	 * @param phoneNumber the phone number
	 * @return User the user
	 */
	public User findByPhoneNumber(String phoneNumber);
	
	/**
	 * Finds a user by its unique columns
	 * 
	 * Generally used for checking if a user exists in the database with one quer with OR clause.
	 * 
	 * @param email the email of the user
	 * @param phoneNumber the phone number of the user
	 * @param username the username of the user
	 * @return User the user
	 */
	public User findByUnique(String email, String phoneNumber, String username);
	
	/**
	 * Add an user to the database without the "merge" checks
	 * 
	 * @param user User the user
	 * @return 
	 */
	public void addUser(User user);
	
	/**
	 * Delete an user
	 * @param user
	 */
	public void deleteUser(User user);
	
}
