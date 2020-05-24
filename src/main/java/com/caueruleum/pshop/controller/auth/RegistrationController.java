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

import com.caueruleum.pshop.dto.RegistrationDTO;
import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.event.OnEmailVerificationTokenRequestedEvent;
import com.caueruleum.pshop.exception.EmailExistsException;
import com.caueruleum.pshop.exception.PhoneNumberExistsException;
import com.caueruleum.pshop.exception.UsernameExistsException;
import com.caueruleum.pshop.model.PshopSuccess;
import com.caueruleum.pshop.service.UserService;

@Controller
@RequestMapping("/auth")
public class RegistrationController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	/**
	 * This method takes care of showing the register view
	 * 
	 * @param model the spring mvc model
	 * @return String
	 */
	@GetMapping("/register")
	public String showRegister(Model model) 
	{
		if (model.getAttribute("newUser") == null)  model.addAttribute("newUser", new RegistrationDTO());
		
		return "register";
	}
	
	/**
	 * This method takes care of registering a new user
 	 * 
	 * @param newUser RegistrationDTO the new user data
	 * @param bindingResult BindingResult the binding result
	 * @param model Model spring mvc model
	 * @param request WebRequest the request
	 * @return
	 */
	@PostMapping("/processRegister")
	public String processRegister(@ModelAttribute("newUser") @Valid final RegistrationDTO newUser,
			final BindingResult bindingResult, Model model, WebRequest request, RedirectAttributes attr) 
	{
		
		if(bindingResult.hasErrors()) 
		{
			attr.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
			attr.addFlashAttribute("newUser", newUser);
			
			return "redirect:/auth/register";
		}
		
		try 
		{
			
			User registered = userService.registerUser(newUser);
			
			eventPublisher.publishEvent(
					new OnEmailVerificationTokenRequestedEvent(
							registered,
							request.getContextPath(),
							request.getLocale()
					));

			attr.addFlashAttribute("success", PshopSuccess.REGISTRATION_SUCCESS);

			return "redirect:/auth/login";
			
		}
		
		catch(EmailExistsException ex) 
		{
			
			bindingResult.rejectValue("email", "errors.registration.email.reject");
			
			attr.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
			attr.addFlashAttribute("newUser", newUser);
			
			return "redirect:/auth/register";
			
		}
		catch (UsernameExistsException ex) 
		{
			bindingResult.rejectValue("username", "errors.registration.username.reject");
			
			attr.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
			attr.addFlashAttribute("newUser", newUser);
			
			return "redirect:/auth/register";
		}
		catch (PhoneNumberExistsException ex) 
		{
			bindingResult.rejectValue("phoneNumber", "errors.registration.phoneNumber.reject");
			
			attr.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
			attr.addFlashAttribute("newUser", newUser);
			
			return "redirect:/auth/register";
		}
		
	}
	
	
	
}
