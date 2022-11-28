package com.shoppingmall.home.model;

import com.shoppingmall.item.model.Item;
import com.shoppingmall.seller.model.Seller;

public class HomeView {
	private Item item;
	private String sellerShopName;
	
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
	
	
}
