package com.shoppingmall.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.basket.bo.BasketBO;
import com.shoppingmall.basket.model.BasketItemView;
import com.shoppingmall.directBasket.bo.DirectBasketBO;
import com.shoppingmall.directBasket.model.DirectBasketItemView;
import com.shoppingmall.order.model.OrderView;

@Service
public class OrderViewBO {

	@Autowired
	private BasketBO basketBO;
	
	@Autowired
	private DirectBasketBO directBasketBO;
	
	public OrderView generateOrderView(Integer directBasketId, List<Integer> basketIdList) {
		
		OrderView orderView = new OrderView();
		
		// 바로 주문 이라면
		if (!ObjectUtils.isEmpty(directBasketId)) {
			DirectBasketItemView directBasketView = directBasketBO.generateDirectBasketItemView(directBasketId);
			orderView.setDirectBasketItemView(directBasketView);
		} else {
			basketBO.get
			orderView.setBasketItemViewList(basketItemViewList);
		}
		return orderView;
	}
}
