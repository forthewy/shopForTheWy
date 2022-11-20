package com.shoppingmall.basketOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.basketOrder.bo.BasketOrderBO;

@RequestMapping("/basket_order")
@RestController
public class BasketOrderRestController {
	
	@Autowired
	private BasketOrderBO basketOrderBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("address") String address,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam(value="directBasketId", required=false) Integer directBasketId,
			@RequestParam(value="directPrice", required=false) Integer directPrice,
			@RequestParam(value="basketIdAndEachTotalPriceList", required=false) List<String> basketIdAndEachTotalPriceList,
			HttpSession session
			) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 주문 도중에 금액을 바꾸는 상점이 있을 수 있기에 가격은 주문서 그대로 가져오기로 한다.
		basketOrderBO.addBasketOrder(userId, phoneNumber, address,  directBasketId, directPrice, basketIdAndEachTotalPriceList);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 300);
		result.put("result", "success");
		
		return result;
	}
}
