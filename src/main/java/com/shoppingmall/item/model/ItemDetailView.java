package com.shoppingmall.item.model;

import java.util.List;

import com.shoppingmall.review.model.ReviewView;
import com.shoppingmall.seller.model.Seller;

public class ItemDetailView {
	
	// 상품
	private Item item;
	
	// 리뷰 목록
	private List<ReviewView> ReviewViewList;
	
	// 상점
	private Seller seller;

	// 상점이 유저가 운영하는 건지 확인
	private boolean isUserSeller;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<ReviewView> getReviewViewList() {
		return ReviewViewList;
	}

	public void setReviewViewList(List<ReviewView> reviewViewList) {
		ReviewViewList = reviewViewList;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public boolean getIsUserSeller() {
		return isUserSeller;
	}

	public void setIsUserSeller(boolean isUserSeller) {
		this.isUserSeller = isUserSeller;
	}
	
}
