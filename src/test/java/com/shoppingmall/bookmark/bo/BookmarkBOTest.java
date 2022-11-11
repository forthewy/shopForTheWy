package com.shoppingmall.bookmark.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookmarkBOTest {
	
	@Autowired
	BookmarkBO bookmarkBO;

	@Test
	void 즐겨찾기조회하기() {
		boolean isbookmarked = bookmarkBO.existBookmark(1, 2);
		assertNotNull(isbookmarked);
	}

}
