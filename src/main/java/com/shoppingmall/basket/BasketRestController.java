package com.shoppingmall.basket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.basket.bo.BasketBO;

@RequestMapping("/basket")
@RestController
public class BasketRestController {
	
	@Autowired
	private BasketBO basketBO;
	
	@RequestMapping("/create")
	public Map<String, Object> create(
			@RequestParam("itemId") int itemId,
			@RequestParam("number") int number,
			HttpSession session) {

		 Map<String, Object> result = new HashMap<>();

		Integer userId = (Integer) session.getAttribute("userId"); 
		 // DB insert
		int row = basketBO.addBasket(userId, itemId, number);
		 
		result.put("code", 300);
	    result.put("result", "success");
		
		return result;
	}
}
