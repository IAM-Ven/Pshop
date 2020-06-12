package com.caueruleum.pshop.service;

import java.io.IOException;
import java.util.List;

import com.caueruleum.pshop.entity.Image;
import com.caueruleum.pshop.uploader.file.PshopFile;

public interface ImageService
{
	/**
	 * Contact the DAO and get all the images
	 * 
	 * @return
	 */
	public List<Image> findAll();

	/**
	 * Find an image by its id
	 * 
	 * @param imageId the image id
	 * @return Image the found image (null if not found)
	 */
	public Image findById(Integer imageId);
	
	/**
	 * Contact the Uploader and upload the image then save it to the database with the metadata.
	 * 
	 * @param image the pshop file
	 */
	public void uploadImage(PshopFile image) throws IOException;

	/**
	 * Contact the dao and save an image to the db
	 * 
	 * @param image Image the image
	 */
	public void saveImage(Image image);
	
	
}
