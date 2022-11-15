package com.shoppingmall.basket.bo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.basket.dao.BasketDAO;
import com.shoppingmall.basket.model.Basket;
import com.shoppingmall.basket.model.BasketItemView;
import com.shoppingmall.item.bo.ItemBO;

@Service
public class BasketBO {
	
	@Autowired
	private BasketDAO basketDAO;
	
	@Autowired
	private ItemBO itemBO;
	
	// 장바구니 목록 가져오기
	public List<Basket> getBasketListByUserId(int userId) {
		return basketDAO.selectBasketListByUserId(userId);
	}
	
	// 장바구니에 아이템 정보 넣기
	public List<BasketItemView> generateBasketItemView(int userId) {
		BasketList basketList = 
	}
	
	// 장바구니 넣기
	public int addBasket(int userId, int itemId, int number) {
		return basketDAO.insertBasket(userId, itemId, number);
	}
	
	//장바구니 삭제
	/*
	 * public int deleteBasket(int id) { return basketDAO.deleteBasket(id); }
	 */
	
	

}
