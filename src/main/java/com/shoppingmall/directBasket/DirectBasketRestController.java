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
	// 바로주문은 주문 안되면 basket이 바로 삭제되도록 만들것!
	
	@Autowired
	private DirectBasketBO directBasketBO;
	
	@RequestMapping("/create")
	public Map<String, Object> create(
			@RequestParam("itemId") int itemId,
			@RequestParam("number") int number,
			HttpSession session) {

		Map<String, Object> result = new HashMap<>();

		Integer userId = (Integer) session.getAttribute("userId"); 
		
		// DB insert 후 selectKey 로 insert 한 값의 pk를 바로 가져온다.
		Integer directBasketId = directBasketBO.addDirectBasket(userId, itemId, number);
		
		if (ObjectUtils.isEmpty(directBasketId)) {
			result.put("code", 500);
			result.put("errorMessage", "바로 주문에 실패했습니다.");
		} else {
			result.put("code", 300);
			result.put("directBasketId", directBasketId);
		}
		
		return result;
	}
}
