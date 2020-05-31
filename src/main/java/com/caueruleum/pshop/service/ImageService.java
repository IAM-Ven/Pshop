package com.caueruleum.pshop.service;

import java.util.List;

import com.caueruleum.pshop.entity.Image;

public interface ImageService
{
	/**
	 * Contact the DAO and get all the images
	 * 
	 * @return
	 */
	public List<Image> findAll();
	
}
