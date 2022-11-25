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
		List<ChatroomView> chatroomViewList = chatroomBO.generateChatroomViewList(userId); 
		
		
		model.addAttribute("chatroomViewList", chatroomViewList);
		model.addAttribute("viewName", "chatroom/chatroomList");
		
		return "template/layout";
	}
	
	@RequestMapping("/seller_chatroom_list_view")
	public String sellerChatroomListView(
			HttpSession session,
			Model model) {
		
		int sellerUserId = (int) session.getAttribute("userId");
		List<ChatroomView> chatroomViewList = chatroomBO.generateSellerChatroomViewList(sellerUserId); 
		
		
		model.addAttribute("chatroomViewList", chatroomViewList);
		model.addAttribute("viewName", "chatroom/sellerChatroomList");
		
		return "template/layout";
	}
}
