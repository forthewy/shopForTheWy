package com.shoppingmall.message;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/message")
@RestController
public class MessageRestController {
	
	
	@PostMapping("/create") 
	public Map<String, Object> create(int sellerId, int userId) {
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 300);
		result.put("result", "success");
		
		return result;
	}

}
