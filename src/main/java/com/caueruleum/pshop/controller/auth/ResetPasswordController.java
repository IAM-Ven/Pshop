package com.caueruleum.pshop.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caueruleum.pshop.dto.TokenUserDTO;
import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.event.OnPasswordResetTokenRequest;
import com.caueruleum.pshop.model.PshopSuccess;
import com.caueruleum.pshop.service.UserService;

@Controller
@RequestMapping("/auth")
public class ResetPasswordController
{
	
	@Autowired
	private UserService userService;
	
	private ApplicationEventPublisher eventPublisher;
	
	/**
	 * This method shows the reset password first stage (type in your username)
	 * 
	 * @param model Model the spring mvc model
	 * @return String the view name
	 */
	@GetMapping("/reset-password")
	public String resetPassword(Model model) 
	{
		
		model.addAttribute("user", new TokenUserDTO());
		
		return "password-token-form";
	}
	
	/**
	 * This sanity checks the username and sends an email to the user`s email.
	 * 
	 * @param model Model the spring mvc model.
	 * @param tokenUser TokenUserDTO the DTO for the token requests.
	 * @param request WebRequest the spring mvc web request.
	 * @param bindingResult BindingResult the spring mvc BindingResult.
	 * @return String the view name
	 */
	@PostMapping("/process-reset-password")
	public String processResetPassword(Model model, @ModelAttribute("user") @Valid final TokenUserDTO tokenUser,
			WebRequest request, final BindingResult bindingResult, RedirectAttributes attr)
	{
		
		if (bindingResult.hasErrors()) 
		{
			attr.addFlashAttribute("org.springframework.validation.BindingResult.tokenUser", bindingResult);
			attr.addFlashAttribute("tokenUser", tokenUser);
			
			return "redirect:/auth/reset-password";
		}
		
		User user = userService.findUserByUsername(tokenUser.getUsername());
		
		if (user == null)
		{
			bindingResult.rejectValue("username", "erros.username.notExists");
			
			attr.addFlashAttribute("org.springframework.validation.BindingResult.tokenUser", bindingResult);
			attr.addFlashAttribute("passwordReset", tokenUser);
			
			return "redirect:/auth/reset-password";
		}
		
		eventPublisher.publishEvent(
				new OnPasswordResetTokenRequest(
						user,
						request.getContextPath(), 
						request.getLocale()));
		
		model.addAttribute("success", PshopSuccess.PASSWORD_RESEND_SUCCESS);
		
		return "password-token-message";
		
	}
}
