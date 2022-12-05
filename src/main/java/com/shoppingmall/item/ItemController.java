package com.shoppingmall.item;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.item.bo.ItemBO;
import com.shoppingmall.item.model.Item;

@RequestMapping("/item")
@Controller
public class ItemController {

	@Autowired
	private ItemBO itemBO;
	
	
	/**
	 * 상품 등록 화면
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/item_create_view")
	public String itemCreateView(
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer) session.getAttribute("userType");
		
		// 비로그인이라면 로그인화면으로 이동한다.
		if (ObjectUtils.isEmpty(userId)) {
			return "redirect:/user/sign_in_view";
		}
		
		model.addAttribute("viewName", "item/itemCreate");
		return "template/layout";
	}
	
	/**
	 * 상품 수정 화면
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/item_update_view")
	public String itemUpdateView(
			@RequestParam("id") int id,
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 비로그인이라면 로그인화면으로 이동한다.
		if (ObjectUtils.isEmpty(userId)) {
			return "redirect:/user/sign_in_view";
		}	
		
		Item item = itemBO.getItemByItemId(id);
		
		model.addAttribute("item", item);
		model.addAttribute("viewName", "item/itemUpdate");
		return "template/layout";
	}
	
}