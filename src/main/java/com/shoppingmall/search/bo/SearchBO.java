package com.shoppingmall.search.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.item.bo.ItemBO;
import com.shoppingmall.item.model.Item;
import com.shoppingmall.search.model.SearchView;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@Service
public class SearchBO {

	@Autowired
	private ItemBO itemBO;
	
	@Autowired
	private SellerBO sellerBO;
	
	// 검색 화면 만들기
	public List<SearchView> generateSearchView(String searchword, Integer page) {
		List<SearchView> searchViewList = new ArrayList<>();
		
		List<Item> itemList = itemBO.getItemListLikesearchWord(searchword, page);
		
		for (Item item : itemList) {
			SearchView searchView = new SearchView();
			searchView.setItem(item);
			Seller seller = sellerBO.getSellerById(item.getSellerId());
			searchView.setSellerShopName(seller.getShopName());
			searchViewList.add(searchView);
		}
		
		return searchViewList;
	}
}
