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
			@Param("point") int point);
	
	public List<Review> selectReviewListByItemId(int itemId);
	
	public Review selectReviewById(int id);
	
	public int deleteReviewById(int id);
	
	public int updateReview(
			@Param("id") int id,
			@Param("content") String content,
			@Param("point") int point);
}
