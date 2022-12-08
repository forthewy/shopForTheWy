package com.shoppingmall.orderLog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogDAO {

	public int insertOrderLog(
			@Param("itemId") int itemId,
			@Param("itemName") String itemName,
			@Param("sellerId") int sellerId,
			@Param("number") int number,
			@Param("price") int price);
}
