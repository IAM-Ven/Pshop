package com.caueruleum.pshop.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TokenUserDTO 
{
	
	@NotNull
	@NotEmpty
	private String username;

	
	public TokenUserDTO(){}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	
	
}
