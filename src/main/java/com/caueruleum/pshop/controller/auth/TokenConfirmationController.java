package com.caueruleum.pshop.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caueruleum.pshop.exception.TokenExpiredException;
import com.caueruleum.pshop.exception.TokenNotFoundException;
import com.caueruleum.pshop.model.PshopError;
import com.caueruleum.pshop.model.PshopSuccess;
import com.caueruleum.pshop.service.VerificationTokenService;

@Controller
@RequestMapping("/auth")
public class TokenConfirmationController
{
	
	@Autowired
	private VerificationTokenService verificationTokenService;
	
	/**
	 * This methods takes care of confirming the registration and allowing the user to log in.
	 * 
	 * @param model Model spring mvc model
	 * @param token String the get request param
	 * @return String
	 */
	@GetMapping("/registration-confirm")
	public String confirmEmail(Model model, @RequestParam("token") String token) 
	{
		try 
		{
			verificationTokenService.confirmToken(token);

			model.addAttribute("success", PshopSuccess.REGISTRATION_CONFIRM_SUCCESS);
			
			return "login";
		}
		catch (TokenNotFoundException ex) 
		{
			model.addAttribute("error", PshopError.TOKEN_NOT_VALID);
			return "verification";
		}
		catch (TokenExpiredException ex) 
		{
			model.addAttribute("error", PshopError.TOKEN_EXPIRED);
			return "verification";
		}
	}

}
