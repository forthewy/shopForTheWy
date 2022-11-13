package com.shoppingmall.item.model;

import java.util.List;

import com.shoppingmall.review.model.Review;
import com.shoppingmall.seller.model.Seller;

public class ItemDetailView {
	
	private Item item;
	
	private List<Review> reviewList;
	
	private Seller seller;

	// 상점이 유저가 운영하는 건지 확인
	private boolean isUserSeller;
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
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
