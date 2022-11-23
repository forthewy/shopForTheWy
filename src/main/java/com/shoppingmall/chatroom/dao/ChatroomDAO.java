package com.shoppingmall.chatroom.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.chatroom.model.Chatroom;

@Repository
public interface ChatroomDAO {

	public Chatroom selectChatroomByUserIdAndSellerId(
			@Param("userId") int userId,
			@Param("sellerId")int sellerId);
	
	public int insertChatroom(
			Map<String, Object> chatroomMap);
}
