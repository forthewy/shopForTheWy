package com.shoppingmall.bookmark;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.bookmark.bo.BookmarkBO;

@RequestMapping("/bookmark")
@RestController
public class BookmarkRestController {

	@Autowired
	private BookmarkBO bookmarkBO;
	
	// 인터셉터에서 비로그인시 로그인 화면 이동하도록 설정하기
	@RequestMapping("/{sellerId}")
	public Map<String, Object> bookmark(
			@PathVariable("sellerId") int sellerId,
			HttpSession session) {
		
	    Integer userId =  (Integer) session.getAttribute("userId");
		
	    int row = bookmarkBO.bookmarkToggle(sellerId, userId);
	    
	    Map<String, Object> result = new HashMap<>();
	    
	    if (row > 0) {
	    	result.put("code", 300);
	    	result.put("result", "success");
	    } else {
	    	result.put("code", 500);
			result.put("errorMessage", "즐겨찾기/ 즐겨찾기 해제에 실패했습니다");
	    }
		
		return result;
	}
}
