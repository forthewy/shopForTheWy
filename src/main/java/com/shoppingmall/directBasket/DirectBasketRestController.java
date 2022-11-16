package com.shoppingmall.directBasket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/direct_basket")
@RestController
public class DirectBasketRestController {
	// 바로주문은 주문 안되면 basket이 바로 삭제되도록 만들것!
	
	@RequestMapping("/create")
	public Map<String, Object> create(
			@RequestParam("itemId") int itemId,
			@RequestParam("number") int number,
			HttpSession session) {

		 Map<String, Object> result = new HashMap<>();

		Integer userId = (Integer) session.getAttribute("userId"); 
		
		// DB insert
		int row = basketBO.addBasket(userId, itemId, number);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "장바구니 넣기에 실패했습니다.");
		}
		
		return result;
	}
