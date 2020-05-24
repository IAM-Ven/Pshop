package com.caueruleum.pshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.caueruleum.pshop.model.AuthorityType;

@Entity
@Table(
		name="authorities",
		uniqueConstraints = @UniqueConstraint(columnNames= {"username", "authority"}))
public class Authority 
{
	// Id of the authority
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique=true, nullable=false)
	private int id;
	
	// The user of the authority
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username", nullable=false)
	private User user;
	
	// Name of the authority
	@Column(name="authority", nullable=false, length = 50)
	private String authority;
	
	public Authority() {}
	
	public Authority(AuthorityType authority) 
	{
		this.authority = authority.getType();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) 
	{
		this.authority = authority;
	}
	
	
	
	

}
