package com.shoppingmall.message;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.message.bo.MessageBO;
import com.shoppingmall.message.model.MessageView;

@RequestMapping("/message")
@Controller
public class MessageController {

	@Autowired
	private MessageBO messageBO;
	
	@RequestMapping("/message_view")
	public String MessageView(
			HttpSession session,
			@RequestParam(value="sellerId", required=false) Integer sellerId,
			@RequestParam(value="chatroomId", required=false) Integer chatroomId,
			Model model) {
		
		int userId = (int) session.getAttribute("userId");
		
		MessageView messageView = messageBO.generateMessageView(userId, sellerId, chatroomId);
		
		model.addAttribute("messageView", messageView);
		model.addAttribute("viewName", "message/message");
		
		return "template/layout";
	}
	
}
