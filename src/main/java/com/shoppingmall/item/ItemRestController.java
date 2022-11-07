package com.shoppingmall.item;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/item")
@Controller
public class ItemRestController {

	@RequestMapping("/create")
	public Map<String, Object> createItem(
			@RequestParam ("name") String name,
			@RequestParam("price") int price,
			@RequestParam("number") int  number,
			@RequestParam(value="content", required=false) String content,
			@RequestParam("sortId") int sortId,
			@RequestParam("thumbnailImg") String thumbnailImg,
			@RequestParam("deliveryPrice") int deliveryPrice,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		
		
		return result;
	}
}
