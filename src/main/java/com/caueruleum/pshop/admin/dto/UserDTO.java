package com.caueruleum.pshop.admin.dto;

import com.caueruleum.pshop.entity.User;

/**
 * DTO for updating/creating user information in the admin module
 * 
 * @author caueruleum
 *
 */
public class UserDTO 
{
	private String username;
	
	private String password;
	
	private boolean isActive;
	
	private String authority;
	
	private String email;
	
	private String phoneNumber;
	
	private String firstName;
	
	private String lastName;
	
	private String address;

	public UserDTO()
	{
	}
	
	public UserDTO(String username, String password, boolean isActive, String authority, String email,
			String phoneNumber, String firstName, String lastName, String address)
	{
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.authority = authority;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}


	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public String getAuthority()
	{
		return authority;
	}

	public void setAuthority(String authority)
	{
		this.authority = authority;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	
	
	
	
	
	
	
}
