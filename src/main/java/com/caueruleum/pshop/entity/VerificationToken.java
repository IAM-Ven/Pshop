package com.caueruleum.pshop.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name="verification_token")
public class VerificationToken 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="v_t_id")
	private int id;
	
	@Column(
			name="v_t_token", 
			unique=true, 
			length=35)
	private String token;
	
	@Column(name="v_t_expiration")
	private Date expiration;
	
	@OneToOne(
			fetch=FetchType.LAZY, 
			cascade= {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="v_t_username")
	private User user;
	
	
	public VerificationToken() {}
	
	public VerificationToken(String token, Date expiration) 
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
