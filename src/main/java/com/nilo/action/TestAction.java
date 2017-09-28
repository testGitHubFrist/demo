package com.nilo.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestAction {
	
	@RequestMapping("test")
	public String test(Model model){
		model.addAttribute("a", "adwdsa");
		return "test-jsp";
	}
}
