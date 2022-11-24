package com.shoppingmall.chatroom.model;

public class ChatroomView {

	private Chatroom chatroom;
	private String userName;
	private String sellerShopName;
	
	public Chatroom getChatroom() {
		return chatroom;
	}
	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSellerShopName() {
		return sellerShopName;
	}
	public void setSellerShopName(String sellerShopName) {
		this.sellerShopName = sellerShopName;
	}
	
}
