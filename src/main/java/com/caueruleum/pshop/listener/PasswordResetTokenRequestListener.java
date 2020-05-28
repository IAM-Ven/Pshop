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
import com.caueruleum.pshop.event.OnPasswordResetTokenRequest;
import com.caueruleum.pshop.service.NotificationService;
import com.caueruleum.pshop.service.PasswordResetTokenService;

@Component
public class PasswordResetTokenRequestListener implements ApplicationListener<OnPasswordResetTokenRequest>
{
	
	@Value("${pshop.domain}")
	private String domain;
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@Autowired
	private NotificationService notificationService;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void onApplicationEvent(OnPasswordResetTokenRequest event) 
	{
		this.confirmRequest(event);
		
	}
	
	private void confirmRequest(OnPasswordResetTokenRequest event) 
	{
		
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		
		passwordResetTokenService.createToken(user, token);
		
		String recipientAddress = user.getUserDetail().getEmail();
		String subject = "Password Reset Confirmation";
		String confirmationLink = event.getAppUrl() + "/auth/reset-password-confirmation?token=" + token;
		
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
