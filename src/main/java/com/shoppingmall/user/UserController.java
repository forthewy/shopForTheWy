package com.shoppingmall.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	
	/**
	 * 회원 가입 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_up_view") 
	public String signUpView(Model model) {
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
		
		return "redirect:/home/home_view";
	}
	
}

	