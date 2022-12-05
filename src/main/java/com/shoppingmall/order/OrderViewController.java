package com.shoppingmall.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
			@RequestParam(value="check", required=false) List<Integer> basketIdList,
			HttpServletResponse response,
			Model model) throws IOException {
		
		
		OrderView orderView = orderViewBO.generateOrderView(directBasketId, basketIdList, response);
		
		model.addAttribute("orderView", orderView);
		model.addAttribute("viewName", "order/orderCreate");
		
		return "template/layout";
	}
	
	
}
