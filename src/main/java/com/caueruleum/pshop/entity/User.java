package com.caueruleum.pshop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User 
{
	// the user`s username, spring security uses this as a primary key
	@Id
	@Column(
			name="username",
			unique=true, 
			nullable=false,
			length = 50)
	private String username;
	
	// password is encrypted with bcrypt
	@Column(name="password", nullable=false, length = 68)
	private String password;
	
	// the user can log in
	@Column(name="enabled", nullable = false)
	private boolean isActive;
	
	// roles of the user
	@OneToOne(fetch=FetchType.LAZY, mappedBy = "user", cascade=CascadeType.ALL)
	private Authority authority;
	
	// orders made by the user
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private List<Order> orders;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy="user", cascade = CascadeType.ALL)
	private UserDetail userDetail;
	
	
	public String getUsername() 
	{
		return username;
	}
	
	public User() 
	{
	}
	
	public User(String username, String password, boolean isActive) 
	{
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public Authority getAuthority() 
	{
		return authority;
	}

	public void setAuthority(Authority authority) 
	{
		this.authority = authority;
	}

	public List<Order> getOrders() 
	{
		return orders;
	}

	public void setOrders(List<Order> orders) 
	{
		this.orders = orders;
	}

	

	public void setActive(boolean isActive) 
	{
		this.isActive = isActive;
	}
	
	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	
	public void addOrder(Order order) 
	{
		if (this.orders == null) 
		{
			this.orders = new ArrayList<Order>();
		}
		
		this.orders.add(order);
		
		order.setUser(this);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", isActive=" + isActive + ", authorities="
				+ authority + ", orders=" + orders + "]";
	}
	
}
