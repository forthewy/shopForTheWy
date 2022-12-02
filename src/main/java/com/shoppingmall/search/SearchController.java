package com.shoppingmall.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.search.bo.SearchBO;
import com.shoppingmall.search.model.SearchView;

@RequestMapping("/search")
@Controller
public class SearchController {

	@Autowired
	private SearchBO searchBO;
	
	@GetMapping("/search_view")
	public String searchView(
			@RequestParam("searchword") String searchword,
			Model model) {
		
		List<SearchView> seachViewList = searchBO.generateSearchView(searchword);
		
		model.addAttribute("seachViewList" ,seachViewList);
		model.addAttribute("viewName", "search/search");
		
		return "template/layout";
	}
}
