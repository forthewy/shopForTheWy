package com.shoppingmall.bookmark.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkDAO {

	public boolean existBookmark(
			@Param("sellerId") int sellerId,
			@Param("userId") Integer userId);
	
	public int insertBookmark(
			@Param("sellerId") int sellerId,
			@Param("userId") int userId);
	
	public int deleteBookmark(
			@Param("sellerId") int sellerId,
			@Param("userId") int userId);
}
