package com.caueruleum.pshop.dao;

import java.util.List;

import com.caueruleum.pshop.entity.Authority;
import com.caueruleum.pshop.model.AuthorityType;

public interface AuthorityDAO {

	/**
	 * Save authority to the database
	 *  
	 * @param authority Authority the authority
	 */
	void saveAuthority(Authority authority);

	/**
	 * Get all the Authorities in the database
	 * 
	 * @return List<Authority> all the authorities
	 */
	public List<Authority> findAll();
	
	/**
	 * Find a authority by its id
	 * 
	 * @param id int the id of the authority
	 * @return Authority the autority
	 */
	public Authority findById(int id);

	/**
	 * Find all with pagination in mind
	 * @param offset the start
	 * @param max the finish
	 * @return List<Authority> list of found authorities
	 */
	public List<Authority> findAllPaginate(int offset, int max);

	/**
	 * Add an authority to the existing list
	 * 
	 * @param auth the current authority
	 * @param type the new role to be added
	 */
	public void addAuthority(Authority auth, AuthorityType type);
	
	/**
	 * Find an authority by type, if it is not found we create it.
	 * 
	 * @param authorityType the type
	 * @return the found authority
	 */
	public Authority findByType(AuthorityType authorityType);
	

}
