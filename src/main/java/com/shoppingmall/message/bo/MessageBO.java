package com.shoppingmall.message.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.chatroom.bo.ChatroomBO;
import com.shoppingmall.chatroom.model.Chatroom;
import com.shoppingmall.message.dao.MessageDAO;
import com.shoppingmall.message.model.Message;
import com.shoppingmall.message.model.MessageView;

@Service
public class MessageBO {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private ChatroomBO chatroomBO;
	
	// 쪽지화면 만들기
	public MessageView generateMessageView(int userId, int sellerId) {
		
		MessageView messageView = new MessageView();
		
		// 쪽지방이 있는지 조회한다.
		Chatroom chatroom = chatroomBO.getChatroomByUserIdAndSellerId(userId, sellerId);
		int chatroomId = 0;
		
		if (ObjectUtils.isEmpty(chatroom)) {
			// 없으면 하나 만든다.
			chatroomId = chatroomBO.addChatroom(userId, sellerId);
		} else {
			chatroomId = chatroom.getId();
		}
		
		// 채팅방 아이디 넣기
		messageView.setChatroomId(chatroomId);
		
		// 메세지 리스트 넣기
		List<Message> messageList = getMessageListByChatroomId(chatroomId);
		messageView.setMessageList(messageList);
		
		return messageView;
	}
	
	public List<Message> getMessageListByChatroomId(int chatroomId) {
		return messageDAO.selectMessageListByChatroomId(chatroomId);
	}
	
	// 쪽지 보내기
	public int addMessage(int senderUserId,String content, int chatroomId) {
		
		return messageDAO.insertMessage(senderUserId, content, chatroomId);
	};
	
}
