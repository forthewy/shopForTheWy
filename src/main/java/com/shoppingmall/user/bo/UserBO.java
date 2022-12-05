package com.shoppingmall.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shoppingmall.user.dao.UserDAO;
import com.shoppingmall.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	// 아이디 중복 확인
	public boolean existLoginId(String loginId) {
		List<String> existLoginId = userDAO.existLoginId(loginId);
		if (CollectionUtils.isEmpty(existLoginId)) {
			return false;
		}
		return true;
	}
	
	// 전화번호 중복 확인
	public boolean existPhoneNumber(String phoneNumber) {
		List<String> existPhoneNumber = userDAO.existPhoneNumber(phoneNumber);
		if (CollectionUtils.isEmpty(existPhoneNumber)) {
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
	
	public User getUserByUserId(int id) {
		return userDAO.selectUserByUserId(id);
	}
	
	// 회원정보 수정
	public int updateUser(int userId, String password, String  name, String address, String phoneNumber) {
		return userDAO.updateUser(userId, password, name, address, phoneNumber);
	}
}
