package com.caueruleum.pshop.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caueruleum.pshop.entity.Image;
import com.caueruleum.pshop.service.ImageService;
import com.caueruleum.pshop.uploader.file.FileType;
import com.caueruleum.pshop.uploader.file.PshopFile;

@Controller
@RequestMapping("/image")
public class ImageController
{
	
	@Autowired
	private ImageService imageService;	
	
	@GetMapping("/id/{imageId}")
	public String showImage(Model model, @PathVariable Integer imageId) 
	{
		Image image = null;
		
		
		if(!(imageId <= 0)) 
		{
			image = this.imageService.findById(imageId);
		}
		
		model.addAttribute("image", image);
		
		return "image";
	}
	
	@GetMapping("/upload/")
	public String showUploadPage(Model model)
	{
		return "image-upload";
	}
	
	
	@PostMapping("/upload/")
	public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes attr) 
	{
		System.out.println("here");
		boolean error = true;
		
		try 
		{
			PshopFile image = new PshopFile.PshopFileBuilder()
					.withMultipart(file)
					.withType(FileType.IMAGE)
					.build();
			
			imageService.uploadImage(image);
			
			error = false;
			
		}
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
