package com.shoppingmall.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.user.dao.UserDAO;
import com.shoppingmall.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	// 아이디 중복 확인
	public boolean existLoginId(String loginId) {
		List<String> existLoginId = userDAO.existLoginId(loginId);
		if (existLoginId.isEmpty()) {
			return false;
		}
		return true;
	}
	
	// 전화번호 중복 확인
	public boolean existPhoneNumber(String phoneNumber) {
		List<String> existPhoneNumber = userDAO.existPhoneNumber(phoneNumber);
		if (existPhoneNumber.isEmpty()) {
			return false;
		}
		return true;
	}
	
	// 회원 가입
	public int addUser(String loginId, String  password, String  name, String address, String phoneNumber) {
		return userDAO.insertUser(loginId, password, name, address, phoneNumber);
	}
	
	// 로그인
	public User getUserByLoginIdAndPassword(String loginId, String  password) {
		return userDAO.selectUserByLoginIdAndPassword(loginId, password);
	}
	
	// loginId로 userId 찾기
	public int getIdByLoginId(String loginId) {
		return userDAO.selectIdByLoginId(loginId);
	}
}
