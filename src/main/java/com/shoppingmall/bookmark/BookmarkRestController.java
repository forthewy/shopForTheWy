package com.shoppingmall.bookmark;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bookmark")
@RestController
public class BookmarkRestController {

	// 인터셉터에서 비로그인시 로그인 화면 이동하도록 설정하기
	@RequestMapping("/{sellerId}")
	public Map<String, Object> bookmark(
			@PathVariable("sellerId") int sellerId,
			HttpSession session) {
		
	    Integer userId =  (Integer) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 300);
		result.put("result", "success");
		
		return result;
	}
}
