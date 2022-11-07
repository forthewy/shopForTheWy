package com.shoppingmall.shop.model;

import java.util.List;

import com.shoppingmall.item.model.Item;
import com.shoppingmall.seller.model.Seller;

public class ShopView {

	private Seller seller;
	
	private List<Item> itemList;

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

}