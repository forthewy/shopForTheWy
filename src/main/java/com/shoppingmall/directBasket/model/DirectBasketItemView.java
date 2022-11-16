package com.shoppingmall.directBasket.model;

import com.shoppingmall.item.model.Item;

public class DirectBasketItemView {

	private Item item;
	
	private DirectBasket directBasket;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public DirectBasket getDirectBasket() {
		return directBasket;
	}

	public void setDirectBasket(DirectBasket directBasket) {
		this.directBasket = directBasket;
	}
	
}
