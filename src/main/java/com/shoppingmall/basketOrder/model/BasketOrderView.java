package com.shoppingmall.basketOrder.model;

import com.shoppingmall.item.model.Item;

public class BasketOrderView {

	private Item item;
	
	private BasketOrder basketOrder;

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
	
	
}
