package com.shoppingmall.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.shoppingmall.message.model.Message;

@Service
public interface MessageDAO {

	public List<Message> selectMessageListByChatroomId(int chatroomId);
}
