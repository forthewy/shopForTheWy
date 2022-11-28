package com.shoppingmall.home.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.home.model.HomeView;
import com.shoppingmall.item.bo.ItemBO;
import com.shoppingmall.item.model.Item;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@Service
public class HomeViewBO {
	
	@Autowired
	private ItemBO itemBO;
	
	@Autowired
	private SellerBO sellerBO;
	
	public List<HomeView> generateHomeView(String sort) {
		
		List<HomeView> homeViewList = new ArrayList<>();
		
		List<Item> itemList = new ArrayList<>();
		
		if (ObjectUtils.isEmpty(sort)) {
			itemList = itemBO.getItemListBySortLimitFour(null);
		} else {
			itemList = itemBO.getItemListBySortLimitFour(sort);
		}
		
		for (Item item: itemList) {
			HomeView homeView = new HomeView();
			homeView.setItem(item);
			
			Seller seller = sellerBO.getSellerById(item.getSellerId());
			homeView.setSellerShopName(seller.getShopName());
			
			homeViewList.add(homeView);
		}
		return homeViewList;
	}
}
