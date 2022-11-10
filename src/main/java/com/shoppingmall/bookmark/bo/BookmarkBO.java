package com.shoppingmall.bookmark.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.bookmark.dao.BookmarkDAO;

@Service
public class BookmarkBO {

	@Autowired
	private BookmarkDAO bookmarkDAO;
	
	/*
	 * public int bookmarkToggle(int sellerId,int userId) { return; }
	 */
	
	
	// 비로그인 --> null --> Integer
	// existBookmark 오류 잡는 중...
	public boolean existBookmarkBysellerIdAndUserId(int sellerId, Integer userId) {
		return bookmarkDAO.existBookmarkBysellerIdAndUserId(sellerId, userId);
	}
}
