package com.shoppingmall.seller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/seller")
@RestController
public class SellerRestController {
	
	@PostMapping("/update")
	public Map<String, Object> update(
			@RequestParam("shopName") String shopName,
			@RequestParam("address") String address,
			@RequestParam("shopPhoneNumber") String shopPhoneNumber,
			@RequestParam(value="bannerImg", required=false) MultipartFile bannerImg,
			@RequestParam(value="shopMainImg", required=false) MultipartFile shopMainImg,
			HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		
		
		
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 300);
		result.put("result", "success");
		
		return result;
	}
}
