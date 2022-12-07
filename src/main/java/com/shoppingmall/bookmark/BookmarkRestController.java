package com.shoppingmall.bookmark;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.bookmark.bo.BookmarkBO;

@RequestMapping("/bookmark")
@RestController
public class BookmarkRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookmarkBO bookmarkBO;

	/**
	 * 즐겨찾기 토글
	 * @param sellerId
	 * @param session
	 * @return
	 */
	@PostMapping("/{sellerId}")
	public Map<String, Object> bookmark(
			@PathVariable("sellerId") int sellerId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		int userId =  (int) session.getAttribute("userId");
		
	    int row = bookmarkBO.bookmarkToggle(sellerId, userId);

	    if (row > 0) {
	    	result.put("code", 300);
	    	result.put("result", "success");
	    	log.info("[즐겨찾기] 즐겨찾기 토글 성공, userId:{}, sellerId:{}", userId, sellerId);
	    } else {
	    	result.put("code", 500);
			result.put("errorMessage", "즐겨찾기/ 즐겨찾기 해제에 실패했습니다");
			log.error("[즐겨찾기] 즐겨찾기 토글 실패, userId:{}, sellerId:{}", userId, sellerId);
	    }
		
		return result;
	}
}
