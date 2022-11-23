package com.shoppingmall.order.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.order.dao.OrderDAO;
import com.shoppingmall.order.model.Order;

@Service
public class OrderBO {
	
	@Autowired
	private OrderDAO orderDAO;
	
	// insert 후 id 가져온다.
	public int addOrder(int userId, String phoneNumber, String address, String name) {
		
		Map<String, Object> orderMap = new HashMap<>();
		orderMap.put("id", null);
		orderMap.put("userId", userId);
		orderMap.put("phoneNumber", phoneNumber);
		orderMap.put("address", address);
		orderMap.put("name", name);
		
		orderDAO.insertOrder(orderMap);
		
		return (int) orderMap.get("id");
	}
	
	// 주문 조회 용
	public List<Integer> getOrderIdListByUserId(int userId) {
		return orderDAO.selectOrderIdListByUserId(userId);
	}
	
	// id로 Order 가져오기
	public Order getOrderById(int id) {
		return orderDAO.selectOrderById(id);
	}
	
	// id와 검색어(이름)으로 가져오기
	public Order getOrderByIdAndName(int id, String name) {
		return orderDAO.selectOrderByIdAndName(id, name);
	}

}
