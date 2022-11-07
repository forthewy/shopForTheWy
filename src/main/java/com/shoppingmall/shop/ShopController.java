package com.shoppingmall.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
			Model model) {
		
		ShopView shop = shopViewBO.generateShopView(sellerLoginId);
		
		model.addAttribute("shop", shop);
		model.addAttribute("viewName", "/shop/shop");
		return "template/layout";
	}
}
