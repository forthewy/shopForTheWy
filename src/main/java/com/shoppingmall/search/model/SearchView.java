package com.shoppingmall.search.model;

import java.util.List;

import com.shoppingmall.item.model.ItemSellerShopName;

public class SearchView {

	private List<ItemSellerShopName> itemSellerShopNameList;

	private int searchCount;
	
	public List<ItemSellerShopName> getItemSellerShopNameList() {
		return itemSellerShopNameList;
	}

	public void setItemSellerShopNameList(List<ItemSellerShopName> itemSellerShopNameList) {
		this.itemSellerShopNameList = itemSellerShopNameList;
	}

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}
	
}
