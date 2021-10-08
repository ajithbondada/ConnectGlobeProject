package com.connectglobe.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.connectglobe.model.Topic;
import com.connectglobe.web.dto.TopicDto;

public interface TopicService extends TopicDetailsService{
	Topic save(TopicDto topicDto);
}
