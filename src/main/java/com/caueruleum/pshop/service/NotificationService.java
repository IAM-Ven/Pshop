package com.caueruleum.pshop.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

public interface NotificationService 
{
	
	/**
	 * Send email in an async fassion, so we won`t block
	 * @param message the message to be sent
	 * @throws MailException if there is an exception
	 */
	void sendEmailAsync(SimpleMailMessage message) throws MailException;

	void sendEmailAsync(String recipientAddress, String subject, String text) throws MailException;

}
