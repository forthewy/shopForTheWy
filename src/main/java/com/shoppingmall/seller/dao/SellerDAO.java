package com.shoppingmall.seller.dao;

import org.springframework.stereotype.Repository;

import com.shoppingmall.seller.model.Seller;

@Repository
public interface SellerDAO {

	public Seller selectSellerByUserId(int userId);
}
