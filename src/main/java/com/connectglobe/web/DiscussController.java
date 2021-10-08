package com.connectglobe.web;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.connectglobe.service.DiscussionService;
import com.connectglobe.web.dto.DiscussDto;


@Controller
@RequestMapping("/discussion")
public class DiscussController {

	private DiscussionService discussionService;
	public DiscussController()
	{
	
	}

	public DiscussController(DiscussionService discussionService) {
		super();
		this.discussionService = discussionService;
	}
	
	@ModelAttribute("discussion")
    public DiscussDto discussDto() {
        return new DiscussDto();
    }
	
	@GetMapping
	public String showDiscussionForm() {
		return "discussion";
	}
	
	@PostMapping
	public String discussion(@ModelAttribute("discussion") DiscussDto discussDto) {
		discussionService.save(discussDto);
		return "redirect:/discuss?success";
	}
}

