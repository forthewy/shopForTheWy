package com.shoppingmall.shop.model;

import java.util.List;

import com.shoppingmall.item.model.Item;
import com.shoppingmall.seller.model.Seller;

public class ShopView {

	private Seller seller;
	
	// 페이지에 나와야 할 아이템 리스트
	private List<Item> itemList;
	
	// 상점이 가진 상품 갯수
	private int itemCount;
	
	// 즐겨찾기 여부
	private boolean isBookmarked;

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

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public void setBookmarked(boolean isBookmarked) {
		this.isBookmarked = isBookmarked;
	}

	public boolean getIsBookmarked() {
		return isBookmarked;
	}

	public void setIsBookmarked(boolean isBookmarked) {
		this.isBookmarked = isBookmarked;
	}
	
}