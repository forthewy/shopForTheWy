package com.shoppingmall.basketOrder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketOrderDAO {

	public int insertDirectBasketOrder(
			@Param("orderId") int orderId,
			@Param("itemId") int itemId,
			@Param("number") int number,
			@Param("price") int price);
	
	public int insertBasketOrder(
			@Param("orderId") int orderId,
			@Param("itemIdNumberPriceMapList") List<Map<String, Integer>> itemIdNumberPriceMapList);
}
