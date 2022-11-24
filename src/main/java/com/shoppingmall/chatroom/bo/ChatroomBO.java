package com.shoppingmall.chatroom.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.chatroom.dao.ChatroomDAO;
import com.shoppingmall.chatroom.model.Chatroom;
import com.shoppingmall.chatroom.model.ChatroomView;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.User;

@Service
public class ChatroomBO {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private SellerBO sellerBO;
	
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
	
	public List<Chatroom> getChatroomListByUserIdOrSellerId(Integer userId, Integer sellerId) {
		return chatroomDAO.selectChatroomListByUserIdOrSellerId(userId, sellerId);
	}
	
	
	// 채팅방 리스트 조회
	public List<ChatroomView> generateChatroomViewList(Integer userId, Integer sellerId) {
		List<ChatroomView> chatroomViewList = new ArrayList<>();
		
		List<Chatroom> chatroomList = getChatroomListByUserIdOrSellerId(userId, sellerId);
		
		for (Chatroom chatroom: chatroomList) {
			ChatroomView chatroomView = new ChatroomView();
			chatroomView.setChatroom(chatroom);
			
			Seller seller = sellerBO.getSellerById(sellerId);
			chatroomView.setSellerShopName(seller.getShopName());
			
			User user = userBO.getUserByUserId(userId);
			chatroomView.setUserName(user.getName());
			
			chatroomViewList.add(chatroomView);
		}
		return chatroomViewList;
	}
	
}
