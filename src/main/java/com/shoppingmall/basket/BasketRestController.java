package com.shoppingmall.basket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.basket.bo.BasketBO;

@RequestMapping("/basket")
@RestController
public class BasketRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BasketBO basketBO;
	
	/**
	 * 장바구니 넣기
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

		int userId = (int) session.getAttribute("userId"); 
		
		// DB insert
		int row = basketBO.addBasket(userId, itemId, number);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
			if (row > 1) {
				log.error("[장바구니 넣기] 장바구니 넣기 중복  userId:{},itemId:{}",userId, itemId);
			}
		} else {
			result.put("code", 500);
			result.put("errorMessage", "장바구니 넣기에 실패했습니다.");
			log.error("[장바구니 넣기] 장바구니 넣기 실패  userId:{},itemId:{}",userId, itemId);
		}
		
		return result;
	}
	
	/**
	 * 장바구니 삭제
	 * @param basketId
	 * @return
	 */
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("basketId") int basketId) {
		
		Map<String, Object> result = new HashMap<>();

		// DB delete
		int row = basketBO.deleteBasket(basketId);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "장바구니 삭제에 실패했습니다.");
		}
		
		return result;
	}
	
}
