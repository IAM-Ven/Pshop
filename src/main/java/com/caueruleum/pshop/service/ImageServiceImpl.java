package com.caueruleum.pshop.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.dao.ImageDAO;
import com.caueruleum.pshop.entity.Image;
import com.caueruleum.pshop.uploader.Uploader;
import com.caueruleum.pshop.uploader.file.PshopFile;

@Service
public class ImageServiceImpl implements ImageService
{
	@Autowired
	private ImageDAO imageDAO;
	
	@Autowired
	private Uploader uploader;
	

	@Value("${pshop.defaults.uploadfolder}")
	private String UPLOAD_FOLDER;
	
	
	@Override
	public List<Image> findAll()
	{
		return imageDAO.findAll();
	}

	@Override
	public Image findById(Integer imageId)
	{
		return this.imageDAO.findImageById(imageId);	
	}
	
	@Override
	public void saveImage(Image image) 
	{
		imageDAO.save(image);
	}
	
	@Override
	@Transactional
	public void uploadImage(PshopFile image) throws IOException
	{
		uploader.upload(image, UPLOAD_FOLDER);
		
		Image img = new Image();
		LocalDate ld = LocalDate.now();
		
		img.setPath(image.getPath());
		// Worry about this later
		img.setTurn(0);
		img.setUploadDate(Date.valueOf(ld));
		
		imageDAO.save(img);
		
		
	}
}
