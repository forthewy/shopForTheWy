package com.shoppingmall.review.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.review.dao.ReviewDAO;
import com.shoppingmall.review.model.Review;
import com.shoppingmall.review.model.ReviewView;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.User;

@Service
public class ReviewBO {

	@Autowired
	private ReviewDAO reviewDAO;
	
	@Autowired
	private UserBO userBO;
	
	public int addReview(int userId, int itemId, String content, double point) {
		return reviewDAO.insertReview(userId, itemId, content, point);
	}
	
	// 아이템의 리뷰 리스트 가져오기
	public List<Review> getReviewListByItemId(int itemId) {
		return reviewDAO.selectReviewListByItemId(itemId);
	}
	
	// 리뷰 뷰 리스트 만들기
	public List<ReviewView> generateReviewView(int itemId) {
		List<ReviewView> reviewViewList = new ArrayList<>();
		
		// 아이템 리뷰 리스트 가져오기
		List<Review> ReviewList = getReviewListByItemId(itemId);
		
		for (Review review: ReviewList) {
			ReviewView reviewView = new ReviewView();
			
			// 리뷰 넣기
			reviewView.setReview(review);
			
			// 유저 정보 넣기
			User user = userBO.getUserByUserId(review.getUserId());
			reviewView.setUserLoginId(user.getLoginId());
			
			reviewViewList.add(reviewView);
		}
		return reviewViewList;
	}

	public Review getReviewById(int id) {
		return reviewDAO.selectReviewById(id);
	}
	
	// 리뷰 삭제
	public int deleteReviewById(int userId, int id) {
		// 리뷰를 가져와서 본인이 작성한 리뷰가 맞는 지 확인
		Review review = getReviewById(id);
		if (review.getUserId() != userId) {
			return 0;
		}
		
		return reviewDAO.deleteReviewById(id);
	}
	
	
	public int updateReview(int userId, int id, String content, double point) {
		return reviewDAO.updateReview(id, content, point);
	}
}
