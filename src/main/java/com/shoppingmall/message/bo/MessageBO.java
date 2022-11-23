package com.shoppingmall.message.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.chatroom.bo.ChatroomBO;
import com.shoppingmall.chatroom.model.Chatroom;
import com.shoppingmall.message.dao.MessageDAO;
import com.shoppingmall.message.model.Message;

@Service
public class MessageBO {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private ChatroomBO chatroomBO;
	
	public List<Message> getMessageListByChatroomId(int userId, int sellerId) {
		
		// 쪽지방이 있는지 조회한다.
		Chatroom chatroom = chatroomBO.getChatroomByUserIdAndSellerId(userId, sellerId);
		int chatroomId = 0;
		
		if (ObjectUtils.isEmpty(chatroom)) {
			// 없으면 하나 만든다.
			chatroomId = chatroomBO.addChatroom(userId, sellerId);
		} else {
			chatroomId = chatroom.getId();
		}
		
		return messageDAO.selectMessageListByChatroomId(chatroomId);
	}
}
