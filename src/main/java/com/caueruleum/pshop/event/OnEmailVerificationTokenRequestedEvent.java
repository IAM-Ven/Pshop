package com.caueruleum.pshop.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.caueruleum.pshop.entity.User;
/**
 * This is an event that is called whenever a token is requseted from somewhere.
 * 
 * @author caueruleum
 *
 */

public class OnEmailVerificationTokenRequestedEvent extends ApplicationEvent 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appUrl;
	private Locale locale;
	private User user;
	
	
	public OnEmailVerificationTokenRequestedEvent(User user, String appUrl, Locale locale) 
	{
		super(user);
		this.appUrl = appUrl;
		this.locale = locale;
		this.user = user;
	}
	
	public String getAppUrl() 
	{
		return appUrl;
	}
	
	public void setAppUrl(String appUrl) 
	{
		this.appUrl = appUrl;
	}
	
	public Locale getLocale() 
	{
		return locale;
	}
	
	public void setLocale(Locale locale) 
	{
		this.locale = locale;
	}
	
	public User getUser() 
	{
		return user;
	}
	
	public void setUser(User user) 
	{
		this.user = user;
	}
	
	
	

}
