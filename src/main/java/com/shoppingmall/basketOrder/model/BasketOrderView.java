package com.shoppingmall.basketOrder.model;

import com.shoppingmall.item.model.Item;
import com.shoppingmall.order.model.Order;

public class BasketOrderView {

	private Item item;
	
	private BasketOrder basketOrder;

	private Order order;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BasketOrder getBasketOrder() {
		return basketOrder;
	}

	public void setBasketOrder(BasketOrder basketOrder) {
		this.basketOrder = basketOrder;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
