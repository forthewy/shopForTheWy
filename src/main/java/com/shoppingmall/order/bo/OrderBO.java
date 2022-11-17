package com.shoppingmall.order.bo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.order.dao.OrderDAO;

@Service
public class OrderBO {
	
	@Autowired
	private OrderDAO orderDAO;
	
	// insert 후 id 가져온다.
	public int addOrder(int userId, String name, String phoneNumber, String address) {
		
		Map<String, Object> orderMap = new HashMap<>();
		orderMap.put("id", null);
		orderMap.put("userId", userId);
		orderMap.put("phoneNumber", phoneNumber);
		orderMap.put("address", address);
		
		orderDAO.insertOrder(orderMap);
		
		return (int) orderMap.get("id");
	}

}
