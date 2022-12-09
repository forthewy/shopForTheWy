package com.shoppingmall.orderLog.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.orderLog.dao.OrderLogDAO;
import com.shoppingmall.orderLog.model.OrderLog;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@Service
public class OrderLogBO {

	@Autowired
	private SellerBO sellerBO;
	
	@Autowired
	private OrderLogDAO orderLogDAO;
	
	public List<OrderLog> getOrderLogList(int userId) {
		Seller seller = sellerBO.getSellerByUserId(userId);
		return orderLogDAO.selectOrderLogList(seller.getId());
	}
}
