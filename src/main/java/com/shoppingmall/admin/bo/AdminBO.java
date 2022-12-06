package com.shoppingmall.admin.bo;

import org.springframework.stereotype.Service;

import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.user.bo.UserBO;

@Service
public class AdminBO {

	private UserBO userBO;

	private SellerBO sellerBO;

	/*
	 * public int acceptSeller(int sellerId, String state) {
	 * 
	 * 
	 * sellerBO.updateSellerStateBySellerId(sellerId, state); Seller seller =
	 * sellerBO.getSellerById(sellerId);
	 * 
	 * userBO.updateUserTypeByUserId(seller.getUserId(), 1); return
	 * 
	 * }
	 */

}
