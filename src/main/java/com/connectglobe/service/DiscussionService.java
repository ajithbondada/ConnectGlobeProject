package com.connectglobe.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.connectglobe.model.Discussion;
import com.connectglobe.web.dto.DiscussDto;
import com.connectglobe.web.dto.UserRegistrationDto;

public interface DiscussionService extends DiscussService{
	Discussion save(DiscussDto discusssDto);
}
