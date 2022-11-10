package com.shoppingmall.bookmark.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BookmarkBOTest {
	
	@Autowired
	BookmarkBO bookmarkBO;

	@Test
	void 즐겨찾기조회하기() {
		boolean isbookmarked = bookmarkBO.existBookmarkBysellerIdAndUserId(1, 2);
	}

}
