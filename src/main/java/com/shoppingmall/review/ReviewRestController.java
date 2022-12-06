package com.shoppingmall.review;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.review.bo.ReviewBO;

@RequestMapping("/review")
@RestController
public class ReviewRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReviewBO reviewBO;

	/**
	 * 리뷰 작성
	 * @param itemId
	 * @param content
	 * @param point
	 * @param session
	 * @return
	 */
	@PostMapping("/create") 
	public Map<String, Object> create(
			@RequestParam("itemId") int itemId,
			@RequestParam("content") String content,
			@RequestParam("point") int point,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		int userId = (int)session.getAttribute("userId");
		
		int row = reviewBO.addReview(userId, itemId, content, point);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "리뷰 등록이 완료되었습니다");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "리뷰 등록에 실패했습니다");
			log.error("[리뷰 등록] 리뷰 등록 실패 itemId:{}, content:{}, point:{}, userId:{}", itemId, content, point, userId); 
		}
		
		return result;
	}
	
	/**
	 * 리뷰 삭제
	 * @param reviewId
	 * @param session
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("reviewId") int reviewId,
			HttpSession session){
		
		int userId = (int)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		int row = reviewBO.deleteReviewById(userId, reviewId);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "리뷰 삭제를 성공했습니다");
			log.info("[리뷰 삭제] 리뷰 삭제 성공 reviewId:{}", reviewId); 
		} else {
			result.put("code", 500);
			result.put("errorMessage", "리뷰 삭제에 실패했습니다");
			log.error("[리뷰 삭제] 리뷰 삭제 실패 userId:{}, reviewId:{}", userId, reviewId); 
		}
		
		return result;
	}
	
	/**
	 * 리뷰 수정
	 * @param reviewId
	 * @param content
	 * @param point
	 * @param session
	 * @return
	 */
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("reviewId") int reviewId,
			@RequestParam("content") String content,
			@RequestParam("point") int point,
			HttpSession session) {
			
		int userId = (int)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		int row = reviewBO.updateReview(userId, reviewId, content, point);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "리뷰 수정을 성공했습니다");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "리뷰 수정에 실패했습니다");
			log.error("[리뷰 수정] 리뷰 수정 실패 userId:{}, reviewId:{}, content:{}, point:{}",userId, reviewId, content, point); 
		}
		
		return result;
	}
}
