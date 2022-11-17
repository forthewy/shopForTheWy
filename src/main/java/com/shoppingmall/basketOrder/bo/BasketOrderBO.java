package com.shoppingmall.basketOrder.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.basketOrder.dao.BasketOrderDAO;
import com.shoppingmall.directBasket.bo.DirectBasketBO;
import com.shoppingmall.directBasket.model.DirectBasket;
import com.shoppingmall.order.bo.OrderBO;
import com.shoppingmall.order.model.Order;

@Service
public class BasketOrderBO {

	@Autowired
	private BasketOrderDAO basketOrderDAO;
	
	@Autowired
	private OrderBO orderBO;
	
	@Autowired
	private DirectBasketBO directBasketBO;
	
	public int addBasketOrder(int userId, String name, String phoneNumber, String address,
			Integer directBasketId, int price) {
		
		// 먼저 order에 등록해서 등록할 orderId를 얻는다.
		int orderId = orderBO.addOrder(userId, name, phoneNumber, address);
		
		DirectBasket directBasket = directBasketBO.getDirectBasketById(directBasketId);
		
		return basketOrderDAO.insertBasketOrder(orderId,  directBasket.getItemId(), directBasket.getNumber(), price);
	}
}
