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
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@Service
public class MessageBO {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private ChatroomBO chatroomBO;
	
	@Autowired
	private SellerBO sellerBO;
	
	// 쪽지화면 만들기 (문의 리스트에서 들어오면 chatroomId 로, 상품 상세에서 들어오면 sellerId로 들어오게 된다.)
	public MessageView generateMessageView(int userId, Integer sellerId, Integer chatroomId) {
		
		MessageView messageView = new MessageView();
		
		// 문의내역에서 들어온게 아니라면(chatroomId가 없다면) 채팅방이 있는지 조회한다.
		if (ObjectUtils.isEmpty(chatroomId)) {
			Chatroom chatroom = chatroomBO.getChatroomByUserIdAndSellerId(userId, sellerId);
			if (ObjectUtils.isEmpty(chatroom)) {
				// 없으면 하나 만든다.
				chatroomId = chatroomBO.addChatroom(userId, sellerId);
			} else {
				chatroomId = chatroom.getId();
			}
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
		Chatroom chatroom = chatroomBO.getChatroomById(chatroomId);
		// 해당 채팅방에 등록 되지 않은 회원은 보낼수 없도록 한다.
		Seller seller = sellerBO.getSellerById(chatroom.getSellerId());
		if (chatroom.getUserId() != senderUserId && seller.getUserId() != senderUserId) {
			return 0;
		}
		
		
		if (senderUserId != chatroom.getUserId()) {
			// 보낸 사람이 상점이라면
			String state = "답변완료";
			chatroomBO.updateChatroomState(chatroomId, state);
		} else {
			// 보낸 사람이 유저라면
			String state = "문의중";
			chatroomBO.updateChatroomState(chatroomId, state);
		}
		
		return messageDAO.insertMessage(senderUserId, content, chatroomId);
	};
	
}
