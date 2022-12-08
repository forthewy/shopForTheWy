package com.shoppingmall.seller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@RequestMapping("/seller")
@Controller
public class SellerController {
	
	@Autowired
	private SellerBO sellerBO;
	
	/**
	 * 상점 수정
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/update_view")
	public String sellerUpdateView(
			HttpSession session,
			Model model) {
		
		int userId = (int) session.getAttribute("userId");
		
		// 상점 홈화면의 주인 유저와 로그인 유저의 아이디가 달라도 설정화면으로는 넘어간다.
		// 단, 본인 설정화면으로 넘어간다. 
		Seller seller = sellerBO.getSellerByUserId(userId);
		
		
		model.addAttribute("seller", seller);
		model.addAttribute("viewName", "seller/sellerUpdate");
		return "template/layout";
	}
	
	/**
	 * 상점 신청 화면
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/create_view")
	public String sellerCreateView(
			HttpSession session,
			Model model) {
		
		int userId = (int) session.getAttribute("userId");
		
		// 중복 신청을 막기 위해 seller DB에 있는 유저인지 확인한다.
		Seller seller = sellerBO.getSellerByUserId(userId);
		
		model.addAttribute("seller", seller);
		model.addAttribute("viewName", "seller/create");
		return "template/layout";
	}
}
