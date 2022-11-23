package com.shoppingmall.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.order.model.Order;

@Repository
public interface OrderDAO {

	public int insertOrder(Map<String,Object> orderMap);
	
	public List<Integer> selectOrderIdListByUserId(int userId);
	
	public Order selectOrderById(int id);
	
	public Order selectOrderByIdAndName(
			@Param("id")int id,
			@Param("name") String name);
	
}
