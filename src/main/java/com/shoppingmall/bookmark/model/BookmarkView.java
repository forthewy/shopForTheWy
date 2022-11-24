package com.shoppingmall.bookmark.model;

import java.util.List;

import com.shoppingmall.seller.model.Seller;

public class BookmarkView {

	private Seller seller;

	private String sellerUserLoginId;

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getSellerUserLoginId() {
		return sellerUserLoginId;
	}

	public void setSellerUserLoginId(String sellerUserLoginId) {
		this.sellerUserLoginId = sellerUserLoginId;
	}
	
}
