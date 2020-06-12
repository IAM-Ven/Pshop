package com.caueruleum.pshop.dao;

import java.util.List;

import com.caueruleum.pshop.entity.Image;

public interface ImageDAO
{

	/**
	 * Find all images that are in the database
	 * 
	 * @return List<Image> the images
	 */
	public List<Image> findAll();

	/**
	 * Find an image by id
	 * 
	 * @param id int the id
	 * @return Image the image
	 */
	public Image findImageById(int id);

	/**
	 * Save or update an image to the database
	 * 
	 * @param image Image the image
	 */
	public void save(Image image);

}
