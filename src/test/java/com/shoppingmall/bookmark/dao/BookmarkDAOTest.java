package com.shoppingmall.bookmark.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookmarkDAOTest {

	@Autowired
	BookmarkDAO bookmarkDAO;
	
	@Test
	void testExistBookmark() {
		boolean result = bookmarkDAO.existBookmark(1, 2);
		assertNotNull(result);
	}

}
