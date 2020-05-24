package com.caueruleum.pshop.service;

import com.caueruleum.pshop.entity.PasswordResetToken;
import com.caueruleum.pshop.entity.User;

public interface PasswordResetTokenService 
{

	public void createToken(User user, String token);

	public PasswordResetToken findTokenByUsername(String username);

	public PasswordResetToken findToken(String token);

	public void removeToken(PasswordResetToken token);

}
