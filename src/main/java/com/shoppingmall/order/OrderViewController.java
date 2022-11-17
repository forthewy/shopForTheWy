package com.shoppingmall.order;

import java.util.List;

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
			@RequestParam(value="basketIdList", required=false) int[] basketIdArr,
			Model model) {
		
		
		OrderView orderView = orderViewBO.generateOrderView(directBasketId, basketIdArr);
		
		model.addAttribute("orderView", orderView);
		model.addAttribute("viewName", "order/orderCreate");
		
		return "template/layout";
	}
}
