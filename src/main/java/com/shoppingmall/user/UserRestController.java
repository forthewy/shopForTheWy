package com.shoppingmall.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.common.EncryptUtils;
import com.shoppingmall.user.bo.UserBO;
import com.shoppingmall.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;

	/**
	 * 아이디 중복 확인
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId")String loginId) {
		Map<String, Object> result = new HashMap<>();
		
		boolean isDuplicateidId = userBO.existLoginId(loginId);
		
		if (isDuplicateidId) {
			result.put("isDuplicateidId", true);
		} else {
			result.put("isDuplicateidId", false);
		}
		
		return result;
	}
	
	/**
	 * 전화번호 중복 확인
	 * @param phoneNumber
	 * @return
	 */
	@RequestMapping("/is_duplicated_phone_number")
	public Map<String, Object> isDuplicatedPhoneNumber(
			@RequestParam("phoneNumber") String phoneNumber) {
		Map<String, Object> result = new HashMap<>();
		
		boolean isDuplicatedPhoneNumber = userBO.existPhoneNumber(phoneNumber);
		
		if (isDuplicatedPhoneNumber) {
			result.put("code", 300);
			result.put("isDuplicatedPhoneNumber", true);
		} else {
			result.put("code", 300);
			result.put("isDuplicatedPhoneNumber", false);
		}
		
		return result;
	}
	
	/**
	 * 회원 가입
	 * @param loginId
	 * @param password
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> isDuplicatedPhoneNumber(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		Map<String, Object> result = new HashMap<>();
	
		String encryptPassword = EncryptUtils.md5(password);
		
		int row = userBO.addUser(loginId, encryptPassword, name, address, phoneNumber);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원 가입에 실패했습니다");
		}
		
		return result;
	}
	
	/**
	 * 로그인
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();

		String encryptPassword = EncryptUtils.md5(password);
		User user = userBO.getUserByLoginIdAndPassword(loginId, encryptPassword);
		
		if (user == null) {
			result.put("code", 500);
			result.put("errorMessage", "아이디 혹은 비밀번호가 틀렸거나 가입하지 않은 회원입니다.");
		} else {
			result.put("code", 300);
			result.put("result", "success");
			
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userPassword", user.getPassword());
			session.setAttribute("userAddress", user.getAddress());
			session.setAttribute("userPhoneNumber", user.getPhoneNumber());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userType", user.getType());
		}
		
		return result;
	}
	
	/**
	 * 회원 정보 수정
	 * @param password
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param session
	 * @return
	 */
	@PostMapping("/update")
	public Map<String, Object> update(
				@RequestParam(value="password", required=false) String password,
				@RequestParam("name") String name,
				@RequestParam("address") String address,
				@RequestParam("phoneNumber") String phoneNumber,
				HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		int userId = (int) session.getAttribute("userId");
		
		
		String encryptPassword = null;
		
		// 비밀번호를 바꾼다면
		if (!ObjectUtils.isEmpty(password)) {
			encryptPassword = EncryptUtils.md5(password);
		}
		
		int row = userBO.updateUser(userId, encryptPassword, name, address, phoneNumber);
		User user = userBO.getUserByUserId(userId);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
			session.setAttribute("userPassword", user.getPassword());
			session.setAttribute("userAddress", user.getAddress());
			session.setAttribute("userPhoneNumber", user.getPhoneNumber());
			session.setAttribute("userName", user.getName());
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원정보 수정에 실패했습니다.");
		}
		
		return result;
	}
}
