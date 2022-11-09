package com.shoppingmall.shop.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.item.bo.ItemBO;
import com.shoppingmall.item.model.Item;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.shop.model.ShopView;

@Service
public class ShopViewBO {
	
	@Autowired
	private SellerBO sellerBO;
	
	@Autowired
	private ItemBO itemBO;
	
	public ShopView generateShopView(String sellerLoginId) {
		
		ShopView shopView = new ShopView();
		
		// 상점객체 가져오기
		Seller seller = sellerBO.getSellerByUserLoginId(sellerLoginId);
		shopView.setSeller(seller);
		
		// 아이템 리스트 가져오기
		List<Item> itemList = itemBO.getItemBySellerId(seller.getId());
		shopView.setItemList(itemList);
		
		return shopView;
	}
}
