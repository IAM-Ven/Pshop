package com.caueruleum.pshop.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.caueruleum.pshop.annotation.FieldsMatch;

@FieldsMatch(field="password", fieldMatch="matchingPassword")
public class PasswordResetDTO
{
	
	private String token;
	
	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String matchingPassword;
	

	public PasswordResetDTO () {}
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public String getMatchingPassword() 
	{
		return matchingPassword;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}


	@Override
	public String toString() {
		return "PasswordResetDTO [token=" + token + ", password=" + password + ", matchingPassword=" + matchingPassword
				+ "]";
	}
	
	
	

}
