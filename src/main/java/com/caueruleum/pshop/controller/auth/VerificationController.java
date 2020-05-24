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
import com.caueruleum.pshop.event.OnEmailVerificationTokenRequestedEvent;
import com.caueruleum.pshop.exception.TokenNotFoundException;
import com.caueruleum.pshop.exception.UserAlreadyActivatedException;
import com.caueruleum.pshop.model.PshopSuccess;
import com.caueruleum.pshop.service.VerificationTokenService;

@Controller
@RequestMapping("/auth")
public class VerificationController
{
	
	@Autowired
	private VerificationTokenService verificationTokenService;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	/**
	 * This method takes care of the get requests for the resend verification view
	 * 
	 * @param model Model spring mvc model
	 * @return String the view name
	 */
	@GetMapping("/resend-verification")
	public String resendVerification(Model model) 
	{
		if (model.getAttribute("user") == null) model.addAttribute("user", new TokenUserDTO());
		
		return "token-form";
	
	}
	
	/**
	 * This method takes care of processing the resend verification token requests.
	 * 
	 * 
	 * @param model Model spring mvc model
	 * @param tokenUser TokenUser the model passed as an post parameter
	 * @param bindingResult BindingResult the binding result.
	 * @param request WebRequest the request
	 * @return String 
	 */
	@PostMapping("/process-resend-verification")
	public String processResendVerification(Model model, @ModelAttribute("user") @Valid final TokenUserDTO tokenUser,
			final BindingResult bindingResult, WebRequest request, RedirectAttributes attr)
	{
		if(bindingResult.hasErrors()) 
		{
			attr.addFlashAttribute("org.springframework.validation.BindingResult.tokenUser", bindingResult);
			attr.addFlashAttribute("tokenUser", tokenUser);
			
			return "redirect:/auth/resend-verification";
		}
		
		try 
		{
			
			User user = verificationTokenService.removeOldVerificationTokenByUsername(tokenUser.getUsername());
			
			eventPublisher.publishEvent(
					new OnEmailVerificationTokenRequestedEvent(
							user,
							request.getContextPath(),
							request.getLocale()
					));
			
			attr.addFlashAttribute("success", PshopSuccess.TOKEN_RESEND_SUCCESS);
			
			return "token-message";
			
		}
		catch (TokenNotFoundException ex) 
		{
			bindingResult.rejectValue("username", "errors.token.invalid");
			
			attr.addFlashAttribute("org.springframework.validation.BindingResult.tokenUser", bindingResult);
			attr.addFlashAttribute("tokenUser", tokenUser);
			
			return "redirect:/auth/resend-verification";
		}
		catch (UserAlreadyActivatedException ex)
		{
			bindingResult.rejectValue("username", "errors.token.alreadyActivated");
			
			attr.addFlashAttribute("org.springframework.validation.BindingResult.tokenUser", bindingResult);
			attr.addFlashAttribute("tokenUser", tokenUser);
			
			return "redirect:/auth/resend-verification";
		
		}
	}
}
