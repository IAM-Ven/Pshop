package com.caueruleum.pshop.service;

import java.util.List;

import com.caueruleum.pshop.entity.Authority;
import com.caueruleum.pshop.model.PageModel;

/**
 * 
 * @author caueruleum
 *
 */
public interface AuthorityService 
{

	/**
	 * Calls the authority DAO layer to get all the authorities in the db
	 * 
	 * @return List<Authority> list of all the authorities
	 */
	public List<Authority> findAll();
	
	/**
	 * Call the DAO leayer to get all the authorities and paginate them
	 * @param page the current page
	 * @return PageModel<Authority> the page
	 */
	public PageModel<Authority> findAllPaginate(int page);
	
	/**
	 * Call the DAO layer to get an authority by its id
	 * 
	 * @param id the id
	 * @return Authority the authority found
	 */
	public Authority findById(Integer id);
	
}
