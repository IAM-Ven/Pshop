package com.caueruleum.pshop.service;

import java.util.List;

import com.caueruleum.pshop.entity.Product;
import com.caueruleum.pshop.model.PageModel;

/**
 * Interface for the product service layer
 * 
 * @author caueruleum
 *
 */
public interface ProductService 
{
	
	/**
	 * Contacts the DAO layer and asks for all the products in the database.
	 * 
	 * @return List<Product> the products
	 */
	public List<Product> findAll();
	
	/**
	 * Contacts the DAO layer and asks for a product with a given id.
	 * 
	 * @param id the id
	 * @return Product the product
	 */
	public Product findById(int id);

	/**
	 * Contacts the dao layer and asks for the most recent products defined by the limit.
	 * 
	 * @param limit the number of the products
	 * @return List<Product> the most recent products
	 */
	public List<Product> findRecent(int limit);

	/**
	 * Contacts the dao layer and asks for paginated products
	 *
	 * @param page int the page currently requested
	 * @param maxPageItems the max items that can be on a page
	 *
	 * @return PageModel<Product> the page of the products
	 */
	public PageModel<Product> findAllPaginate(int page, int maxPageItems);
}
