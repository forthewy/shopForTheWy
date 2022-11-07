package com.shoppingmall.shop.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.shop.model.ShopView;

@Service
public class ShopViewBO {
	
	@Autowired
	private SellerBO sellerBO;
	
	public ShopView generateShopView(String sellerLoginId) {
		
		ShopView shopView = new ShopView();
		
		// 상점객체 가져오기
		Seller seller = sellerBO.getSellerByUserLoginId(sellerLoginId);
		shopView.setSeller(seller);
		
		return shopView;
	}
}
