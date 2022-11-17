package com.shoppingmall.directBasket.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.directBasket.model.DirectBasket;

@Repository
public interface DirectBasketDAO {
	
	public Integer insertDirectBasket(Map<String,Object> directBasketMap);
	
	public DirectBasket selectDirectBasketById(int id);
	
	public DirectBasket selectDirectBasketByUserIdAndItemId(
			@Param("userId") int userId,
			@Param("itemId") int itemId
			);
	
	public int deleteDirectBasketById(int id);
}
