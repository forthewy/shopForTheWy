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
	
	
	public List<Item> selectItemBySellerId(int sellerId);
	
	public Item selectItemByItemId(int itemId);
}
