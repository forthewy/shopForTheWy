package com.shoppingmall.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.order.bo.OrderViewBO;
import com.shoppingmall.order.model.OrderView;

@RequestMapping("/order")
@Controller
public class OrderViewController {
	
	@Autowired
	private OrderViewBO orderViewBO;
	
	@RequestMapping("/order_create_view")
	public String orderCreateView(
			@RequestParam(value="directBasketId", required=false) Integer directBasketId,
			Model model) {
		
		
		OrderView orderView = orderViewBO.generateOrderView(directBasketId);
		
		model.addAttribute("orderView", orderView);
		model.addAttribute("viewName", "order/orderCreate");
		
		return "template/layout";
	}
}
