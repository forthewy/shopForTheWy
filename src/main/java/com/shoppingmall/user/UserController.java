package com.shoppingmall.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	/**
	 * 로그인 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_in_view") 
	public String signInView(
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 이미 로그인 했다면
		if (!ObjectUtils.isEmpty(userId)) {
			return "redirect:/home/home_view";
		}
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	

	 /**
	  * 회원 가입 화면
	  * @param session
	  * @param model
	  * @return
	  */
	@RequestMapping("/sign_up_view") 
	public String signUpView(
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 이미 로그인 했다면
		if (!ObjectUtils.isEmpty(userId)) {
			return "redirect:/home/home_view";
		}
		
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	/**
	 * 로그 아웃
	 * @param session
	 * @return
	 */
	@RequestMapping("/sign_out") 
	public String signOut(HttpSession session) {
		
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userPassword");
		session.removeAttribute("userAddress");
		session.removeAttribute("userPhoneNumber");
		session.removeAttribute("userName");
		session.removeAttribute("userType");
		session.removeAttribute("lastLookItem");
		
		return "redirect:/home/home_view";
	}
	
	/**
	 * 회원정보 수정 화면
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/update_view")
	public String updateView(
			HttpSession session
			,Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 비로그인이라면
		if (ObjectUtils.isEmpty(userId)) {
			return "redirect:/user/sign_in_view";
		}
		
		model.addAttribute("viewName", "user/update");
		return "template/layout";
	}
}

	