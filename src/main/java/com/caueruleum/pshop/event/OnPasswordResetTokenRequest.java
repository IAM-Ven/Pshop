package com.caueruleum.pshop.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.caueruleum.pshop.entity.User;

public class OnPasswordResetTokenRequest extends ApplicationEvent 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7797441116961868774L;
	private User user;
	private String appUrl;
	private Locale locale;
	
	public OnPasswordResetTokenRequest(User user, String appUrl, Locale locale) 
	{
		super(user);
		this.user = user;
		this.appUrl = appUrl;
		this.locale = locale;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
}
