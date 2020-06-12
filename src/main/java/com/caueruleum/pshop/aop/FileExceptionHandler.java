package com.caueruleum.pshop.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileExceptionHandler
{
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleFileSizeLimitExceededException(MaxUploadSizeExceededException ex, RedirectAttributes attr) 
	{
		attr.addAttribute("error", true);
		
		return "redirect:/file/upload-status";
		
	}
	
}
