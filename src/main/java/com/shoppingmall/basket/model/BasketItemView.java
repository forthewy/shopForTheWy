package com.shoppingmall.basket.model;

import com.shoppingmall.item.model.Item;

public class BasketItemView {

	private Item item;
	
	private Basket Basket;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Basket getBasket() {
		return Basket;
	}

	public void setBasket(Basket basket) {
		Basket = basket;
	}
	
	
}
