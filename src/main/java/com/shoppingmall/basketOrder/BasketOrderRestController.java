package com.shoppingmall.basketOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.basketOrder.bo.BasketOrderBO;

@RequestMapping("/basket_order")
@RestController
public class BasketOrderRestController {
	
	@Autowired
	private BasketOrderBO basketOrderBO;
	
	/**
	 * 주문
	 * @param address
	 * @param phoneNumber
	 * @param name
	 * @param directBasketId
	 * @param directPrice
	 * @param basketIdAndEachTotalPriceList
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("address") String address,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("name") String name,
			@RequestParam(value="directBasketId", required=false) Integer directBasketId,
			@RequestParam(value="directPrice", required=false) Integer directPrice,
			@RequestParam(value="basketIdAndEachTotalPriceList", required=false) List<String> basketIdAndEachTotalPriceList,
			HttpSession session
			) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 주문 도중에 금액을 바꾸는 상점이 있을 수 있기에 가격은 주문서 그대로 가져오기로 한다.
		int row = basketOrderBO.addBasketOrder(userId, phoneNumber,  address, name,  directBasketId, directPrice, basketIdAndEachTotalPriceList);
		
		Map<String, Object> result = new HashMap<>();
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "주문에 실패했습니다");
		}
		
		return result;
	}
	
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("basketOrderId") int basketOrderId,
			@RequestParam("state") String state) {
		
		Map<String, Object> result = new HashMap<>();
		int row = basketOrderBO.updateBasketOrder(basketOrderId, state);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "주문 상태 변경에 실패했습니다.");
		}
		
		return result;
	}
}
