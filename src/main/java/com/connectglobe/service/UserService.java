package com.connectglobe.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.connectglobe.model.User;
import com.connectglobe.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
