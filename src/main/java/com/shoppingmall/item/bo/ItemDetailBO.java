package com.shoppingmall.item.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.item.model.Item;
import com.shoppingmall.item.model.ItemDetailView;
import com.shoppingmall.review.bo.ReviewBO;
import com.shoppingmall.review.model.ReviewView;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.user.bo.UserBO;

@Service
public class ItemDetailBO {

	@Autowired
	private ItemBO itemBO;
	
	@Autowired
	private SellerBO sellerBO;

	@Autowired
	private ReviewBO reviewBO;
	
	@Autowired
	private UserBO userBO;
	
	// 비로그인도 상세 화면 볼수 있도록 Integer userId
	public ItemDetailView generateItemDetailView(int itemId, Integer userId) {
		
		ItemDetailView itemDetailView = new ItemDetailView();
		
		// 아이템 가져오기
		Item item = itemBO.getItemByItemId(itemId);
		itemDetailView.setItem(item);
		
		// 상점 가져오기
		Seller seller = sellerBO.getSellerById(item.getSellerId());
		itemDetailView.setSeller(seller);
		
		// 상점이 유저와 일치하는 지
		boolean isUserSeller = false;
		
		// seller를 구했을때 null 이 아니고
		Seller userSeller = sellerBO.getSellerByUserId(userId);
		if (!ObjectUtils.isEmpty(userSeller)) {
			// sellerId 가 일치하다면 true
			if (userSeller.getId() == item.getSellerId()) {
				isUserSeller = true;
			}
		}
		itemDetailView.setIsUserSeller(isUserSeller);
		
		String sellerLoginId = userBO.getUserByUserId(seller.getUserId()).getLoginId();
		itemDetailView.setSellerLoginId(sellerLoginId);
		
		// 리뷰 뷰 리스트
		List<ReviewView> reviewViewList = reviewBO.generateReviewView(itemId);
		itemDetailView.setReviewViewList(reviewViewList);
		
		return itemDetailView;
	}
}
