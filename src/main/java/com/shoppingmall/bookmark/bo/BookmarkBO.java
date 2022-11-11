package com.shoppingmall.bookmark.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.bookmark.dao.BookmarkDAO;

@Service
public class BookmarkBO {

	@Autowired
	private BookmarkDAO bookmarkDAO;
	
	public int bookmarkToggle(int sellerId, int userId) {
		
		int row = 0;
		
		if (existBookmark(sellerId, userId)) {
			//즐겨찾기 취소
			row = deleteBookmark(sellerId, userId);
		} else {
			// 즐겨찾기 추가
			row = createBookmark(sellerId, userId);
		}
		
		return row; 
	}
	
	
	public int createBookmark(int sellerId, int userId) {
		return bookmarkDAO.insertBookmark(sellerId, userId);
	}
	
	public int deleteBookmark(int sellerId, int userId) {
		return bookmarkDAO.deleteBookmark(sellerId, userId);
	}

	public boolean existBookmark(int sellerId, Integer userId) {
		return bookmarkDAO.existBookmark(sellerId, userId);
	}


	
}
