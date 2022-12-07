package com.shoppingmall.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.admin.bo.AdminBO;

@RequestMapping("/admin")
@RestController
public class AdminRestController {

	@Autowired
	private AdminBO adminBO;
	
	@PostMapping("/seller_accept")
	public Map<String, Object> acceptSeller(
			@RequestParam("sellerId") int sellerId) {
	
		Map<String, Object> result = new HashMap<>();

		int row = adminBO.acceptSeller(sellerId);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "상점 승인에 실패했습니다.");
		}
    	
    	return result;
	}
}
