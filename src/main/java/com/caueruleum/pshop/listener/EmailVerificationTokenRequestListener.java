package com.caueruleum.pshop.listener;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.caueruleum.pshop.entity.User;
import com.caueruleum.pshop.event.OnEmailVerificationTokenRequestedEvent;
import com.caueruleum.pshop.service.NotificationService;
import com.caueruleum.pshop.service.VerificationTokenService;

/**
 * Listener for the OnTokenRequestedEvent.
 * 
 * This creates a token and sends it to the user`s email.
 * 
 * @author caueruleum
 *
 */

@Component
public class EmailVerificationTokenRequestListener implements ApplicationListener<OnEmailVerificationTokenRequestedEvent>
{
	@Value("${pshop.domain}")
	private String domain;
	
	@Autowired
	private VerificationTokenService verificationTokenService;
	
	@Autowired
	private NotificationService notificationService;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public void onApplicationEvent(OnEmailVerificationTokenRequestedEvent event) 
	{
		
		this.confirmRegistration(event);
		
	}
	
	private void confirmRegistration(OnEmailVerificationTokenRequestedEvent event) 
	{
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		
		verificationTokenService.createToken(user, token);
		
		String recipientAddress = user.getUserDetail().getEmail();
		String subject = "Registration Confirmation";
		String confirmationLink = event.getAppUrl() + "/auth/registration-confirm?token=" + token;
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipientAddress);
		message.setSubject(subject);
		message.setText(domain + confirmationLink);
		
		try 
		{
			notificationService.sendEmailAsync(message);
		}
		catch (MailException ex) 
		{
			logger.log(Level.SEVERE, ex.toString());
		}
	}
	
	
}
