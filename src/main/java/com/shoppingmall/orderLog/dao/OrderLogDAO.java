package com.shoppingmall.orderLog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.orderLog.model.OrderLog;

@Repository
public interface OrderLogDAO {

	public int insertOrderLog(
			@Param("itemId") int itemId,
			@Param("itemName") String itemName,
			@Param("sellerId") int sellerId,
			@Param("number") int number,
			@Param("price") int price);
	
	public List<OrderLog> selectOrderLogList (int sellerId);
}
