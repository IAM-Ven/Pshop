package com.caueruleum.pshop.controller.auth;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caueruleum.pshop.dto.PasswordResetDTO;
import com.caueruleum.pshop.entity.PasswordResetToken;
import com.caueruleum.pshop.exception.PasswordMatchesException;
import com.caueruleum.pshop.model.PshopError;
import com.caueruleum.pshop.model.PshopSuccess;
import com.caueruleum.pshop.service.PasswordResetTokenService;
import com.caueruleum.pshop.service.UserService;

@Controller
@RequestMapping("/auth")
public class ResetPasswordConfirmationController
{
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * This takes care of the sanity checks of the token.
	 * 
	 * If the checks pass you are given a form to write your new password.
	 * 
	 * @param model Model the spring mvc model.
	 * @param token String the get request param
	 * @return String the view name.
	 */
	@GetMapping("/reset-password-confirmation")
	public String showResetPasswordConfirmation(Model model, @RequestParam("token") String token) 
	{
		
		if (!model.containsAttribute("passwordReset")) 
		{
			PasswordResetDTO passwordResetDTO = new PasswordResetDTO();
			passwordResetDTO.setToken(token);
		
			model.addAttribute("passwordReset", passwordResetDTO);
		}
		if(model.getAttribute("error") == null) 
		{
			PasswordResetToken resetToken = passwordResetTokenService.findToken(token); 
			Calendar cal = Calendar.getInstance();
			
			
			if (resetToken == null) 
			{
				model.addAttribute("error", PshopError.TOKEN_NOT_VALID);
				
				return "password-reset-form";
			}
			else if(resetToken.getExpiration().getTime() - cal.getTime().getTime() <= 0) 
			{
				model.addAttribute("error", PshopError.TOKEN_EXPIRED);
				
				return "token-message";
			}
		}
		
		
		
		return "password-reset-form";
	}
	
	/**
	 * This takes care of changing the user`s password.
	 * 
	 * If the checks pass your password is changed and you can login with your new password.
	 * 
	 * @param model Model the spring mvc model
	 * @param passwordReset PasswordResetDTO this is an object that keeps the token and the password.
	 * @return String the view name.
	 */
	@PostMapping("/process-reset-password-confirmation")
	public String processResetPasswordConfirmation(Model model,
			@ModelAttribute("passwordReset") @Valid final PasswordResetDTO passwordReset,
			final BindingResult bindingResult, RedirectAttributes attr) 
	{
		if(bindingResult.hasErrors()) 
		{
			
			attr.addFlashAttribute("org.springframework.validation.BindingResult.passwordReset", bindingResult);
			attr.addFlashAttribute("passwordReset", passwordReset);
			
			attr.addAttribute("token", passwordReset.getToken());
			
			return "redirect:/auth/reset-password-confirmation";
		}
		
		PasswordResetToken resetToken = passwordResetTokenService.findToken(passwordReset.getToken());
		
		if(resetToken == null) 
		{
			attr.addFlashAttribute("error", PshopError.TOKEN_NOT_VALID);
			attr.addAttribute("token", passwordReset.getToken());
			
			return "redirect:/auth/reset-password-confirmation";
		}
		
		try 
		{
			userService.changePassword(passwordReset.getPassword(), resetToken.getUser());
			
			passwordResetTokenService.removeToken(resetToken);
			
			model.addAttribute("success", PshopSuccess.PASSWORD_CHANGE_SUCCESS);
			
			return "password-token-message";
		
		}
		catch (PasswordMatchesException ex) 
		{
			attr.addFlashAttribute("error", PshopError.PasswordMatchesError);
			
			return "redirect:/auth/reset-password-confirmation?token=" + passwordReset.getToken();
		}
		
	}
}
