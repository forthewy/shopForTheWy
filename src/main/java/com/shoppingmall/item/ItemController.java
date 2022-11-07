package com.shoppingmall.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/item")
@Controller
public class ItemController {

	@RequestMapping("/item_create_view")
	public String 
			Model model) {
