package com.shoppingmall.admin.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.user.bo.UserBO;

@Service
public class AdminBO {

	@Autowired
	private UserBO userBO;

	@Autowired
	private SellerBO sellerBO;

	
	public int acceptSeller(int sellerId) {

		int row = 0;
		
		// seller 승인
		row = sellerBO.updateSellerStateBySellerId(sellerId, "승인"); 
		Seller seller = sellerBO.getSellerById(sellerId);
		
		// userType 변경
		row = userBO.updateUserTypeByUserId(seller.getUserId(), 2); 
		
	return row;
	}
	
	
	public List<Map<String, Integer>> generateAdminSellerRequestView() {
		
		List<Map<String, Integer>> sellerShopNameAndIdList = new ArrayList<>();

		List<Seller> sellerApplyList = sellerBO.getSellerByState("미승인");
		
		for (Seller seller : sellerApplyList) {
			Map<String, Integer> sellerApplyMap = new HashMap<>();
			sellerApplyMap.put(seller.getShopName(), seller.getId());
			sellerShopNameAndIdList.add(sellerApplyMap);
		}
		
		return sellerShopNameAndIdList;
	}


}
