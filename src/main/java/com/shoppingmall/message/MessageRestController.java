package com.shoppingmall.message;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.message.bo.MessageBO;

@RequestMapping("/message")
@RestController
public class MessageRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MessageBO messageBO;
	
	/**
	 * 쪽지 보내기
	 * @param chatroomId
	 * @param content
	 * @param session
	 * @return
	 */
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
			log.info("[메세지 전송] 메세지 전송 성공  userId:{}, chatroomId:{}, content:{}", userId, chatroomId, content);
		} else {
			result.put("code", 500);
			result.put("errorMessage", "문의(쪽지) 보내기에 실패했습니다.");
			log.error("[메세지 전송] 메세지 전송 실패  userId:{}, chatroomId:{}, content:{}", userId, chatroomId, content);
		}
		
		return result;
	}

}
