package com.shoppingmall.basket.model;

import com.shoppingmall.item.model.Item;

public class BasketItemView {

	private Item item;
	
	private String sellerShopName;
	
	private Basket Basket;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public String getSellerShopName() {
		return sellerShopName;
	}

	public void setSellerShopName(String sellerShopName) {
		this.sellerShopName = sellerShopName;
	}

	public Basket getBasket() {
		return Basket;
	}

	public void setBasket(Basket basket) {
		Basket = basket;
	}
	
	
}
