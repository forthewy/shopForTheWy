package com.shoppingmall.shop;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.shop.bo.ShopViewBO;
import com.shoppingmall.shop.model.ShopView;

@RequestMapping("/shop")
@Controller
public class ShopController {
	
	@Autowired
	private ShopViewBO shopViewBO;
	
	@RequestMapping("/shop_view/{sellerLoginId}") 
	public String signInView(
			@PathVariable("sellerLoginId") String sellerLoginId,
			@RequestParam(value="page", defaultValue = "1") Integer page,
			HttpSession session,
			Model model) {
		
		// 상점 홈화면은 누구나 볼수 있으므로 Integer
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 페이지는 null 이면 1로 들어온다.
		ShopView shop = shopViewBO.generateShopView(sellerLoginId, userId, page);
		
		model.addAttribute("sellerLoginId",sellerLoginId);
		model.addAttribute("shop", shop);
		model.addAttribute("currentPage", page);
		model.addAttribute("viewName", "/shop/shop");
		return "template/layout";
	}
}
