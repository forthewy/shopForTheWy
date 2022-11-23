package com.shoppingmall.basketOrder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.basketOrder.model.BasketOrder;

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
	
	public List<BasketOrder> selectBasketOrderByOrderIdList(List<Integer> orderIdList);
	
	public List<BasketOrder> selectBasketOrderByItemIdList(List<Integer> itemIdList);

	public List<BasketOrder> selectBasketOrderByItemIdListAndState(
			@Param("itemIdList")List<Integer> itemIdList,
			@Param("state") String state);
	
	public int updateBasketOrder(
			@Param("id") int id,
			@Param("state") String state);
}
