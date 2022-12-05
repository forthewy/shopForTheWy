package com.shoppingmall.bookmark.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.bookmark.dao.BookmarkDAO;
import com.shoppingmall.bookmark.model.BookmarkView;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.User;

@Service
public class BookmarkBO {

	@Autowired
	private BookmarkDAO bookmarkDAO;
	
	@Autowired
	private SellerBO sellerBO;
	
	@Autowired
	private UserBO userBO;
	
	public int bookmarkToggle(int sellerId, int userId) {
		
		int row = 0;
		
		if (existBookmark(sellerId, userId)) {
			//즐겨찾기 취소
			row = deleteBookmark(sellerId, userId);
		} else {
			// 즐겨찾기 추가
			row = addBookmark(sellerId, userId);
		}
		return row; 
	}
	
	
	public int addBookmark(int sellerId, int userId) {
		return bookmarkDAO.insertBookmark(sellerId, userId);
	}
	
	public int deleteBookmark(int sellerId, int userId) {
		return bookmarkDAO.deleteBookmark(sellerId, userId);
	}

	public boolean existBookmark(int sellerId, Integer userId) {
		return bookmarkDAO.existBookmark(sellerId, userId);
	}
	
	public List<Integer> getSellerIdListByUserId(int userId) {
		return bookmarkDAO.selectSellerIdListByUserId(userId);
	}

	// 유저아이디로 북마크 뷰 리스트 만들기
	public List<BookmarkView> generateBookmarkViewListByUserId(int userId) {
		
		List<BookmarkView> bookmarkViewList = new ArrayList<>();
		
		// 북마크한 sellerId 리스트
		List<Integer> sellerIdList = getSellerIdListByUserId(userId);
		if (ObjectUtils.isEmpty(sellerIdList)) {
			return null;
		}
		// SellerList
		List<Seller> sellerList = sellerBO.getSellerListByUserId(sellerIdList);
		
		for (Seller seller : sellerList) {
			BookmarkView bookmarkView = new BookmarkView();
			// seller 넣기
			bookmarkView.setSeller(seller);
			// sellerLoginId
			User user = userBO.getUserByUserId(seller.getUserId());
			bookmarkView.setSellerUserLoginId(user.getLoginId());
				
			bookmarkViewList.add(bookmarkView);
		}
		return bookmarkViewList;
	}
}
