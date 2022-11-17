package com.shoppingmall.order.model;

import java.util.List;

import com.shoppingmall.basket.model.BasketItemView;
import com.shoppingmall.directBasket.model.DirectBasketItemView;

public class OrderView {
	
	private List<BasketItemView> basketItemViewList;
	private DirectBasketItemView directBasketItemView;
	
	
	public List<BasketItemView> getBasketItemViewList() {
		return basketItemViewList;
	}
	public void setBasketItemViewList(List<BasketItemView> basketItemViewList) {
		this.basketItemViewList = basketItemViewList;
	}
	public DirectBasketItemView getDirectBasketItemView() {
		return directBasketItemView;
	}
	public void setDirectBasketItemView(DirectBasketItemView directBasketItemView) {
		this.directBasketItemView = directBasketItemView;
	}
}
	
	