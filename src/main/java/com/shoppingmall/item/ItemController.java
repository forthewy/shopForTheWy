package com.shoppingmall.item;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@RequestMapping("/item_create_view")
	public String itemCreateView(
			HttpSession session,
			Model model) {
		
		int sellerId = (int) session.getAttribute("userId");
		
		model.addAttribute("sellerId", sellerId);
		model.addAttribute("viewName", "item/itemCreate");
		return "template/layout";
	}
	
}