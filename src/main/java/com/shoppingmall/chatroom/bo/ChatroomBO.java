package com.shoppingmall.chatroom.bo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.chatroom.dao.ChatroomDAO;
import com.shoppingmall.chatroom.model.Chatroom;

@Service
public class ChatroomBO {

	@Autowired
	private ChatroomDAO chatroomDAO;
	
	public Chatroom getChatroomByUserIdAndSellerId(int userId, int sellerId) {
		return chatroomDAO.selectChatroomByUserIdAndSellerId(userId, sellerId);
	}
	
	public int addChatroom(int userId, int sellerId) {
		
		Map<String, Object> chatroomMap = new HashMap<>();
		chatroomMap.put("id", null);
		chatroomMap.put("userId", userId);
		chatroomMap.put("sellerId", sellerId);
		chatroomDAO.insertChatroom(chatroomMap);
		
		return (int)chatroomMap.get("id");
	}
}
