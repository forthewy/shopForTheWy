package com.shoppingmall.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
@Controller
public class OrderController {

	@RequestMapping("/order_create_view")
	public String orderCreateView(Model model) {
		
		model.addAttribute("viewName", "order/orderCreate");
		
		return "template/layout";
	}
}
