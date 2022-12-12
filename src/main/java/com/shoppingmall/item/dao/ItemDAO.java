package com.shoppingmall.item.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.item.model.Item;

@Repository
public interface ItemDAO {
	
	public int insertItem(
			@Param("sellerId") int sellerId,
			@Param("name") String name,
			@Param("number") int number,
			@Param("price") int price,
			@Param("content") String content,
			@Param("sort") String sort,
			@Param("imagePath") String imagePath,
			@Param("deliveryPrice") int deliveryPrice
			);
	
	public int updateItem(
		@Param("name") String name,
		@Param("number") int number,
		@Param("price") int price,
		@Param("content") String content,
		@Param("sort") String sort,
		@Param("imagePath") String imagePath,
		@Param("deliveryPrice") int deliveryPrice,
		@Param("itemId") int itemId
		);
	
	public int deleteItem(int itemId);
	
	public List<Item> selectItemBySellerId(int sellerId);
	
	public List<Item> selectItemListBySortLimitFour(String sort);
	
	public List<Integer> selectItemIdListBySellerId(int sellerId);
	
	public List<Item> selectItemBySellerIdLimitPage (
			@Param("sellerId") int sellerId,
			@Param("offsetNum") int offsetNum);
	
	public int selectCountLikesearchWord(String searchWord);
	
	public List<Item> selectItemListLikeSearchWord(
			@Param("searchWord") String searchWord,
			@Param("page") int page);
	
	public Item selectItemByItemId(int itemId);
	
	public int selectItemCountBySellerId(int sellerId);
}
