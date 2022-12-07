package com.shoppingmall.directBasket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.directBasket.bo.DirectBasketBO;

@RequestMapping("/direct_basket")
@RestController
public class DirectBasketRestController {
	
	@Autowired
	private DirectBasketBO directBasketBO;
	
	/**
	 * 바로 주문
	 * @param itemId
	 * @param number
	 * @param session
	 * @return
	 */
	@RequestMapping("/create")
	public Map<String, Object> create(
			@RequestParam("itemId") int itemId,
			@RequestParam("number") int number,
			HttpSession session) {

		Map<String, Object> result = new HashMap<>();

		Integer userId = (Integer) session.getAttribute("userId"); 
		
		// DB insert 
		Integer directBasketId = directBasketBO.addDirectBasket(userId, itemId, number);
		
		if (!ObjectUtils.isEmpty(directBasketId)) {
			result.put("code", 300);
			result.put("directBasketId", directBasketId);
		} else {
			result.put("code", 500);
			result.put("errorMessage", "바로 주문에 실패했습니다");
		}
		
		return result;
	}
}
