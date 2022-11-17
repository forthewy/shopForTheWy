package com.shoppingmall.basket;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.basket.bo.BasketBO;
import com.shoppingmall.basket.model.BasketItemView;

@RequestMapping("/basket")
@Controller
public class BasketController {

	@Autowired
	private BasketBO basketBO;
	
	@RequestMapping("/basket_list_view")
	public String basketView(
			HttpSession session,
			Model model) {
		
		// 로그인되지 않은 상태라면 인터셉터에서 로그인으로 이동
		int userId = (int)session.getAttribute("userId");
		
		List<BasketItemView> baskteItemViewList  = basketBO.generateBasketItemViewListByUserId(userId);
		
		model.addAttribute("basketItemList", baskteItemViewList);
		model.addAttribute("viewName", "basket/basketList");
		return "template/layout";
	}
}
