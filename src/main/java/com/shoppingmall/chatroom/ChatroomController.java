package com.shoppingmall.chatroom;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chatroom")
@Controller
public class ChatroomController {

	@RequestMapping("/chatroom_list_view")
	public String chatroomListView(
			HttpSession session,
			Model model) {
		
		model.addAttribute("viewName", "chatroom/chatroomList");
		
		return "template/layout";
	}
}
