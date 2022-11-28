package com.shoppingmall.item;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.item.bo.ItemDetailBO;
import com.shoppingmall.item.model.ItemDetailView;

@Controller
@RequestMapping("/item")
public class ItemViewController {

	@Autowired
	private ItemDetailBO itemDetailBO;
	
	@RequestMapping("/item_detail_view")
	public String itemDetailView(
			@RequestParam("itemId") int itemId,
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		ItemDetailView itemDetailView = itemDetailBO.generateItemDetailView(itemId, userId);
		
		// 최근 본 상품 세션에 넣기
		session.setAttribute("lastLookItem", itemDetailView.getItem());
		model.addAttribute("itemDetailView", itemDetailView);
		model.addAttribute("viewName", "item/itemDetail");
		return "template/layout";
	}
	
}
