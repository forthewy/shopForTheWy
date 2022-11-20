package com.shoppingmall.basket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.basket.model.Basket;

@Repository
public interface BasketDAO {

	public List<Basket> selectBasketListByUserId(int userId);
	
	public List<Basket> selectBasketListByBasketIdList(List<Integer> basketIdList);
	
	public Basket selectBasketById(int id);
	
	public int insertBasket(
			@Param("userId") int userId,
			@Param("itemId") int itemId,
			@Param("number") int number);
	
	public Basket existBasketByUserIdAndItemId(
			@Param("userId") int userId,
			@Param("itemId") int itemId);
	
	public int updateBasket(
			@Param("userId") int userId,
			@Param("itemId") int itemId,
			@Param("number") int number);
	
	public int deleteBasket(int id);
}
