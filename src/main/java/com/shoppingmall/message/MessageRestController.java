package com.shoppingmall.message;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.message.bo.MessageBO;

@RequestMapping("/message")
@RestController
public class MessageRestController {
	
	@Autowired
	private MessageBO messageBO;
	
	@PostMapping("/create") 
	public Map<String, Object> create(
			@RequestParam("chatroomId") int chatroomId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		int userId = (int) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		int row = messageBO.addMessage(userId, content, chatroomId);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "문의(쪽지) 보내기에 실패했습니다.");
		}
		
		return result;
	}

}
