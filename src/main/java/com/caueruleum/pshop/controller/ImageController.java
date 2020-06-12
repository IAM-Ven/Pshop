package com.caueruleum.pshop.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caueruleum.pshop.entity.Image;
import com.caueruleum.pshop.service.ImageService;
import com.caueruleum.pshop.uploader.file.FileType;
import com.caueruleum.pshop.uploader.file.PshopFile;

/**
 * Handles all image based operations
 * 
 * @author caueruleum
 *
 */
@Controller
@RequestMapping("/image")
public class ImageController
{
	@Autowired
	private ImageService imageService;	
	
	/**
	 * 
	 * Get an image
	 * 
	 * @param model Model the model
	 * @param imageId the id of the image
	 * @return @ResponseBody byte[] the image
	 */
	@GetMapping(value = "/{imageId}/", 
			produces = MediaType.IMAGE_JPEG_VALUE)
	public  @ResponseBody byte[] showImage(Model model, @PathVariable Integer imageId) 
	{
		Image image = null;
		
		
		if(!(imageId <= 0)) 
		{
			image = this.imageService.findById(imageId);
		}
		
		try 
		{
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("static/upload/" + image.getPath());
		
			return IOUtils.toByteArray(in);
		}
		catch(Exception ex) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
		}
	}
	
	/**
	 * Show the image upload form
	 * 
	 * @param model Model the mvc model
	 * @return String the view
	 */
	@GetMapping("/upload/")
	public String showUploadPage(Model model)
	{
		return "image-upload";
	}
	
	/**
	 * Upload an image (the post handler)
	 * 
	 * @param file MultipartFile the file
	 * @param attr RedirectAttributes the attr
	 * @return String the redirect instruction
	 */
	@PostMapping("/upload/")
	public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes attr) 
	{
		boolean error = true;
		
		try 
		{
			// Build an image file
			PshopFile image = new PshopFile.PshopFileBuilder()
					.withMultipart(file)
					.withType(FileType.IMAGE)
					.build();
			// Try to upload it 
			imageService.uploadImage(image);
			
			// Signal that there were no errors if we got here
			error = false;
			
		}
		// Handle the exceptions, for now only kinda log them
		
		// Implement a propper logging
		catch(FileNotFoundException ex) 
		{
			System.out.println("log");
		}
		catch(IOException ex) 
		{
			System.out.println("log2");
		}
		
		attr.addAttribute("error", error);
		
		return "redirect:/file/upload-status";
		
	}
}
