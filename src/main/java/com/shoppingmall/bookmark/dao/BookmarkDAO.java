package com.shoppingmall.bookmark.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkDAO {

	public boolean existBookmarkBysellerIdAndUserId(
			@Param("sellerId") int sellerId,
			@Param("userId") Integer userId);
}
