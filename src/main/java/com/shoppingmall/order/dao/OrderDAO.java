package com.shoppingmall.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO {

	public int insertOrder(Map<String,Object> orderMap);
	
	public List<Integer> selectOrderIdListByUserId(int userId);
	
}
