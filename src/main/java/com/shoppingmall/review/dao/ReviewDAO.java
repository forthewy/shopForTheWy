package com.shoppingmall.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.review.model.Review;

@Repository
public interface ReviewDAO {

	public int insertReview(
			@Param("userId") int userId,
			@Param("itemId") int itemId,
			@Param("content") String content,
			@Param("point") double point);
	
	public List<Review> selectReviewListByItemId(int itemId);
}
