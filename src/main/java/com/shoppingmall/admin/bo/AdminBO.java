package com.shoppingmall.admin.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.user.bo.UserBO;

@Service
public class AdminBO {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserBO userBO;

	@Autowired
	private SellerBO sellerBO;

	// 상점 승인
	public int acceptSeller(int sellerId) {

		int row1 = 0;
		
		// seller 승인
		row1 = sellerBO.updateSellerStateBySellerId(sellerId, "승인"); 
		if (row1 > 0) {
			log.info("[상점 승인] seller의 state 변경 성공 sellerId:{}", sellerId);
		} else {
			log.error("[상점 승인] seller의 state 변경 실패 sellerId:{}", sellerId);
		}
		Seller seller = sellerBO.getSellerById(sellerId);
		
		// userType 변경
		int row2 =  userBO.updateUserTypeByUserId(seller.getUserId(), 2); 
		
		if (row2 > 0) {
			log.info("[상점 승인] user의 type 변경 성공 userId:{}", seller.getUserId());
		} else {
			log.error("[상점 승인] user의 type 변경 실패 userId:{}", seller.getUserId());
		}
		
		int result = row1 + row2;
		return result;
	}
	
	
	// 상점 승인 화면 만들기
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
