package com.shoppingmall.basket.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shoppingmall.basket.model.Basket;

@Repository
public interface BasketDAO {

	public List<Basket> selectBasketListByUserId(int userId);
	
	
}
