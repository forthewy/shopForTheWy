package com.shoppingmall.basket.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.basket.dao.BasketDAO;
import com.shoppingmall.basket.model.Basket;

@Service
public class BasketBO {
	
	@Autowired
	private BasketDAO basketDAO;
	
	// 장바구니 목록 가져오기
	public List<Basket> getBasketListByUserId(int userId) {
		return basketDAO.selectBasketListByUserId(userId);
	}
	
	// 장바구니 넣기
	/* public int insertBasket(); */
}
