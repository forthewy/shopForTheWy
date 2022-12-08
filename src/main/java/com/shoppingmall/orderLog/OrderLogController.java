package com.shoppingmall.orderLog;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order_log")
@Controller
public class OrderLogController {

	@RequestMapping("/stats_view")
	public String orderLogStatsView(HttpSession session 
									,Model model) {
		
		model.addAttribute("viewName", "orderLog/stats");
	
		return "template/layout";
	}
}
