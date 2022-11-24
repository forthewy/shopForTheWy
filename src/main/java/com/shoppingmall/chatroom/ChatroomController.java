package com.shoppingmall.chatroom;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.chatroom.bo.ChatroomBO;
import com.shoppingmall.chatroom.model.ChatroomView;

@RequestMapping("/chatroom")
@Controller
public class ChatroomController {

	@Autowired
	private ChatroomBO chatroomBO;	
	
	@RequestMapping("/chatroom_list_view")
	public String chatroomListView(
			HttpSession session,
			Model model) {
		
		int userId = (int) session.getAttribute("userId");
		List<ChatroomView> chatroomViewList = chatroomBO.generateChatroomViewList(userId, null); 
		
		
		model.addAttribute("chatroomViewList", chatroomViewList);
		model.addAttribute("viewName", "chatroom/chatroomList");
		
		return "template/layout";
	}
}
