package com.shoppingmall.item;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/item")
@Controller
public class ItemController {

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