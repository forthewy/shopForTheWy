package com.shoppingmall.search.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.item.bo.ItemBO;
import com.shoppingmall.item.model.ItemSellerShopName;
import com.shoppingmall.search.model.SearchView;
import com.shoppingmall.seller.bo.SellerBO;

@Service
public class SearchBO {

	@Autowired
	private ItemBO itemBO;
	
	
	// 검색 화면 만들기
	public SearchView generateSearchView(String searchword, Integer page) {
		SearchView searchView = new SearchView();
		
		// 총 검색 갯수
		int searchCount = itemBO.getCountLikesearchWord(searchword);
		searchView.setSearchCount(searchCount);
		
		List<ItemSellerShopName> itemSellerShopNameList =  itemBO.getItemSellerShopNameListLikeSearchWord(searchword, page);
		searchView.setItemSellerShopNameList(itemSellerShopNameList);
		
		return searchView;
	}
}
