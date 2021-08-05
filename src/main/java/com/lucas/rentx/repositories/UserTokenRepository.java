package com.lucas.rentx.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.rentx.entities.UserToken;

public interface UserTokenRepository  extends JpaRepository<UserToken, UUID>{
	UserToken findByRefreshToken(UUID refreshToken);
}
