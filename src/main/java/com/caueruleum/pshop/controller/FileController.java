package com.caueruleum.pshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/file")
public class FileController
{
	@GetMapping("/upload-status")
	public String showStatus(Model model, @RequestParam(required = true) Boolean error) 
	{
		String strErr = "";
		
		if(error == false) 
		{
			strErr = "Качването е успешно";
		}else 
		{
			strErr = "Качването не е успешно";
		}
		
		model.addAttribute("error", strErr);
		return "upload-status";
	}
}
