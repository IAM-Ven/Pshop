package com.caueruleum.pshop.service;

import java.util.List;

import com.caueruleum.pshop.dto.RegistrationDTO;
import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.exception.EmailExistsException;
import com.caueruleum.pshop.exception.PasswordMatchesException;
import com.caueruleum.pshop.exception.PhoneNumberExistsException;
import com.caueruleum.pshop.exception.UsernameExistsException;

public interface UserService 
{
	
	/**
	 * This registers an user based on a registration form submission
	 * 
	 * throws a exception if something of the unique fields exists
	 * 
	 * @param registrationDTO the DTO containing the input
	 * @return User the new user
	 * @throws EmailExistsException if this email is found in the database
	 * @throws PhoneNumberExistsException if this phone is found in the database
	 * @throws UsernameExistsException if this username is found in the database
	 */
	public User registerUser(RegistrationDTO registrationDTO) throws EmailExistsException, 
	PhoneNumberExistsException, UsernameExistsException;
	
	/**
	 * Save a user to the database.
	 * @param user the user.
	 */
	public void saveUser(User user);
	
	/**
	 * Call the dao to find a user by its username
	 * @param username the username
	 * @return the found username
	 */
	public User findUserByUsername(String username);
	
	/**
	 * Changes the password and does sanity checks
	 * @param newPassword the new password
	 * @param user the user
	 * @throws PasswordMatchesException if the password we are trying to change is the same as the one in the db
	 */
	public void changePassword(String newPassword, User user) throws PasswordMatchesException;

	/**
	 * Get a list of all the users
	 * @return List<User> list of all the users in the db
	 */
	public List<User> findAll();

	/**
	 * Call the dao to delete a user from the db
	 * @param user User user the user
	 */
	public void deleteUser(User user);

}
