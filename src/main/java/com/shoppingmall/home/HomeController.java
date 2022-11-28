package com.shoppingmall.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.home.bo.HomeViewBO;
import com.shoppingmall.home.model.HomeView;

@Controller
public class HomeController {

	@Autowired
	private HomeViewBO homeViewBO;
	
	@RequestMapping("/home/home_view") 
	public String homeView(Model model) {
		
		List<HomeView> homeViewList = homeViewBO.generateHomeView();
		
		model.addAttribute("homeViewList", homeViewList);
		model.addAttribute("viewName", "home/home");
		return "template/layout";
	}
}
