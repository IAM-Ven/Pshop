package com.caueruleum.pshop.dao;

import com.caueruleum.pshop.entity.PasswordResetToken;

public interface PasswordResetTokenDAO {

	public void saveToken(PasswordResetToken token);

	public void removeToken(PasswordResetToken token);

	public PasswordResetToken findTokenByUsername(String username);

	public PasswordResetToken findToken(String token);

}
