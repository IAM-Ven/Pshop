package com.caueruleum.pshop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="password_reset_token")
public class PasswordResetToken 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="p_r_t_id")
	private int id;
	
	@Column(name="p_r_t_token", unique=true)
	private String token;
	
	@Column(name="p_r_t_expiration")
	private Date expiration;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="p_r_t_username")
	private User user;

	public PasswordResetToken() {}
	
	public PasswordResetToken(String token, Date expiration) 
	{
		this.token = token;
		this.expiration = expiration;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getToken() 
	{
		return token;
	}

	public void setToken(String token) 
	{
		this.token = token;
	}

	public Date getExpiration() 
	{
		return expiration;
	}

	public void setExpiration(Date expiration) 
	{
		this.expiration = expiration;
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
