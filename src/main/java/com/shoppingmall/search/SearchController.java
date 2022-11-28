package com.shoppingmall.search;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/search")
@Controller
public class SearchController {

	@GetMapping("/search_view")
	public String searchView(
			@RequestParam("searchword") String searchword,
			Model model) {
		model.addAttribute("viewName", "search/search");
		
		return "template/layout";
	}
}
