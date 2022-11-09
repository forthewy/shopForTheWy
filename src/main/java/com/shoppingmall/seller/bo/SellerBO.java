package com.shoppingmall.seller.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.seller.dao.SellerDAO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.user.bo.UserBO;

@Service
public class SellerBO {

	@Autowired
	private SellerDAO sellerDAO;
	
	@Autowired
	private UserBO userBO;
	
	public Seller getSellerByUserLoginId(String loginId) {
		int userId = userBO.getIdByLoginId(loginId);
		
		return getSellerByUserId(userId);
	}
	
	public Seller getSellerByUserId(int userId) {
		return sellerDAO.selectSellerByUserId(userId);
	}
	
	public int updateSellerByUserId() {
		return sellerDAO.updateSellerByUserId()
	}
}
