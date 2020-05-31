package com.caueruleum.pshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caueruleum.pshop.dao.ImageDAO;
import com.caueruleum.pshop.entity.Image;

@Service
public class ImageServiceImpl implements ImageService
{
	@Autowired
	private ImageDAO imageDAO;
	
	@Override
	public List<Image> findAll()
	{
		return imageDAO.findAll();
	}
	
}
