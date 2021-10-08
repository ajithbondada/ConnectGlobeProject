package com.connectglobe.web;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.connectglobe.service.postreportService;
import com.connectglobe.web.dto.PostReportDto;

@Controller
@RequestMapping("/postreport")
public class PostReportController {

	private postreportService postReportService;
	
	public PostReportController()
	{
		
	}

	public PostReportController(postreportService postReportService) {
		super();
		this. postReportService= postReportService;
	}
	
	@ModelAttribute("postreport")
    public PostReportDto postreportDto() {
        return new PostReportDto();
    }
	
	@GetMapping
	public String showPostReportForm() {
		return "postreport";
	}
	
	@PostMapping
	public String postreport(@ModelAttribute("postreport") PostReportDto postReportDto) {
		postReportService.save(postReportDto);
		return "redirect:/postreport?success";
	}
}

