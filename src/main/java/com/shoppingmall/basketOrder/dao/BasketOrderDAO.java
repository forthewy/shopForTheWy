package com.shoppingmall.basketOrder.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketOrderDAO {

	public int insertBasketOrder(
			@Param("orderId") int orderId,
			@Param("itemId") int itemId,
			@Param("number") int number,
			@Param("price") int price);
}
