package com.shoppingmall.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/home/home_view") 
	public String homeView(Model model) {
		model.addAttribute("viewName", "home/home");
		return "template/layout";
	}
}
