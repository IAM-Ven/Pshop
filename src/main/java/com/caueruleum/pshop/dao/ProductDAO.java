package com.caueruleum.pshop.dao;

import java.util.List;

import com.caueruleum.pshop.entity.Product;

/**
 * The product DAO layer
 * 
 * @author caueruleum
 * @since 2020-02-21
 */
public interface ProductDAO 
{
	/**
	 * Get a list of all the products stored in the database
	 * 
	 * @return List<Product> a list of all the products stored in the database.
	 */
	public List<Product> findAll();
	
	/**
	 * Get a product by its id
	 * 
	 * @param id the if of the product
	 * @return Product the product found
	 */
	public Product getById(int id);
	
	/**
	 * Get the most recent products
	 * 
	 * Do a query on all the products ordering them by date desc
	 * and returning only the given limit of them.
	 * 
	 * ex: getRecent(5) -> most 5 recent products
	 * 
	 * @param limit the number of products to return
	 * @return List<Product> the list of found products
	 */
	public List<Product> getRecent(int limit);

	/**
	 * Get all products with pagination
	 * 
	 * @param offset start
	 * @param max finish
	 * @return List<Product> the list of products
	 */
	public List<Product> findAllPaginate(int offset, int max);

	/**
	 * Save or update a product
	 * 
	 * @param product Product the product
	 */
	public void save(Product product);

	/**
	 * Delete a product
	 * 
	 * @param product Product the product
	 */
	public void delete(Product product);
	
	// This will be available when I write the Aspect layer
	// public List<Product> getPopular(int limit);

}
