package com.shoppingmall.item.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
			@Param("imagePath") int deliveryPrice
			);
}
