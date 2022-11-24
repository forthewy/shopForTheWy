package com.shoppingmall.message.model;

import java.util.List;

public class MessageView {

	private int chatroomId;
	
	private List<Message> messageList;

	public int getChatroomId() {
		return chatroomId;
	}

	public void setChatroomId(int chatroomId) {
		this.chatroomId = chatroomId;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	
}
