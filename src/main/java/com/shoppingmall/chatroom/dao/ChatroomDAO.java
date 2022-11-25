package com.shoppingmall.chatroom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.chatroom.model.Chatroom;

@Repository
public interface ChatroomDAO {

	public Chatroom selectChatroomByUserIdAndSellerId(
			@Param("userId") int userId,
			@Param("sellerId")int sellerId);
	
	public int insertChatroom(Map<String, Object> chatroomMap);
	
	public List<Chatroom> selectChatroomListByUserId(int userId);
	
	public List<Chatroom> selectChatroomListBySellerId(int sellerId);
	
	public Chatroom selectChatroomById(int id);
	
	public int updateChatroomState(
			@Param("id") int id,
			@Param("state") String state);
}
