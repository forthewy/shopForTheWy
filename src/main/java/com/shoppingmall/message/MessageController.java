package com.shoppingmall.message;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/message")
@Controller
public class MessageController {

	@RequestMapping("/message_view")
	public String MessageView(
			HttpSession session,
			@RequestParam("sellerId") int sellerId,
			Model model) {
		
		model.addAttribute("viewName", "message/message");
		
		return "template/layout";
	}
}
