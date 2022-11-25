package com.shoppingmall.chatroom.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
	
	// 유저의 문의한 리스트 조회
	public List<Chatroom> getChatroomListByUserId(int userId) {
		return chatroomDAO.selectChatroomListByUserId(userId);
	}
	
	
	// 상점의 문의리스트 조회
	public List<Chatroom> getChatroomListBySellerId(int sellerId) {
		return chatroomDAO.selectChatroomListBySellerId(sellerId);
	}
	
	
	// 유저의 채팅방 리스트 조회
	public List<ChatroomView> generateChatroomViewList(int userId) {
		List<ChatroomView> chatroomViewList = new ArrayList<>();
		
		
		List<Chatroom> chatroomList = new ArrayList<>();
		chatroomList = getChatroomListByUserId(userId);
		
		for (Chatroom chatroom: chatroomList) {
			ChatroomView chatroomView = new ChatroomView();
			chatroomView.setChatroom(chatroom);
			
			Seller seller = sellerBO.getSellerById(chatroom.getSellerId());
			chatroomView.setSellerShopName(seller.getShopName());
			
			chatroomViewList.add(chatroomView);
		}
		return chatroomViewList;
	}
	
	// 상점이 조회할때
	public List<ChatroomView> generateSellerChatroomViewList(int sellerUserId) {
		List<ChatroomView> chatroomViewList = new ArrayList<>();
		
		
		List<Chatroom> chatroomList = new ArrayList<>();
		Seller seller = sellerBO.getSellerByUserId(sellerUserId);
		
		chatroomList = getChatroomListBySellerId(seller.getId());
		
		for (Chatroom chatroom: chatroomList) {
			ChatroomView chatroomView = new ChatroomView();
			chatroomView.setChatroom(chatroom);
			
			User user = userBO.getUserByUserId(chatroom.getUserId());
			chatroomView.setUserName(user.getName());
			
			chatroomViewList.add(chatroomView);
		}
		return chatroomViewList;
	}
	
	// 채팅방 아이디로 채팅방 조회
	public Chatroom getChatroomById(int id) {
		return chatroomDAO.selectChatroomById(id);
	}
	
	public int updateChatroomState(int id, String state) {
		return chatroomDAO.updateChatroomState(id, state);
	}
	
}
