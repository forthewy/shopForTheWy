package com.shoppingmall.seller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@RequestMapping("/seller")
@Controller
public class SellerController {
	
	@Autowired
	private SellerBO sellerBO;
	
	@RequestMapping("/update_view")
	public String sellerUpdateView(
			HttpSession session,
			Model model) {
		
		int userId = (int) session.getAttribute("userId");
		
		Seller seller = sellerBO.getSellerByUserId(userId);
		
		model.addAttribute("seller", seller);
		model.addAttribute("viewName", "seller/sellerUpdate");
		return "template/layout";
	}
}
