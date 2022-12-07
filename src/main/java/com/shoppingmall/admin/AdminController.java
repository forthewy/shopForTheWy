package com.shoppingmall.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.admin.bo.AdminBO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminBO adminBO;
	
	
	@RequestMapping("/seller_request_view")
	public String adminView(Model model) {
		
		// 상점 신청 목록
		List<Map<String, Integer>> sellerShopNameAndIdList = adminBO.generateAdminSellerRequestView();
		
		model.addAttribute("sellerShopNameAndIdList", sellerShopNameAndIdList);
		
		return "admin/sellerRequest";
	}
}
