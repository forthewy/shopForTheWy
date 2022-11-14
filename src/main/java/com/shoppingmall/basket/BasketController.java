package com.shoppingmall.basket;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.basket.bo.BasketBO;
import com.shoppingmall.basket.model.Basket;

@RequestMapping("/basket")
@Controller
public class BasketController {

	@Autowired
	private BasketBO basketBO;
	
	@RequestMapping("/basket_list_view")
	public String basketView(
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<Basket> basketList = basketBO.getBasketListByUserId(userId);
				
		model.addAttribute("viewName", "basket/basketList");
		return "template/layout";
	}
}
