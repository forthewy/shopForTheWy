package com.shoppingmall.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SellerBO sellerBO;
	
	@RequestMapping("/seller_request_view")
	public String adminView(Model model) {
		
		List<Seller> sellerList = sellerBO.getSellerByState("미승인");
		
		model.addAttribute("sellerList", sellerList);
		
		return "admin/sellerRequest";
	}
}
