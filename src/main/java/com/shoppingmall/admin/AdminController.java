package com.shoppingmall.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/admin_view")
	public String adminView() {

		return "admin/sellerRequest";
	}
}
