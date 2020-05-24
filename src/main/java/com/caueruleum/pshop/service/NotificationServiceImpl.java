package com.caueruleum.pshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService
{
	@Autowired
	private JavaMailSender sender;
	
	
	@Override
	@Async
	public void sendEmailAsync(SimpleMailMessage message) throws MailException
	{
			sender.send(message);	
	}
	
	@Override
	@Async
	public void sendEmailAsync(String recipientAddress, String subject, String text) throws MailException
	{
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(recipientAddress);
		message.setSubject(subject);
		message.setText(text);
		
		sender.send(message);
	}
	
}
