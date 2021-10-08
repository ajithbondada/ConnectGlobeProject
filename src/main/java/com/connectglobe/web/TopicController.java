package com.connectglobe.web;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.connectglobe.service.TopicService;
import com.connectglobe.web.dto.TopicDto;

@Controller
@RequestMapping("/topic")
public class TopicController {

	private TopicService topicService;
	public TopicController()
	{
		
	}

	public TopicController(TopicService topicService) {
		super();
		this.topicService = topicService;
	}
	
	@ModelAttribute("user")
    public TopicDto topicDto() {
        return new TopicDto();
    }
	
	@GetMapping
	public String showTopicForm() {
		return "topic";
	}
	
	@PostMapping
	public String topic(@ModelAttribute("topic") TopicDto topicDto) {
		topicService.save(topicDto);
		return "redirect:/topic?success";
	}
}

