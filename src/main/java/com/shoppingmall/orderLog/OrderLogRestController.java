package com.shoppingmall.orderLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingmall.orderLog.bo.OrderLogBO;
import com.shoppingmall.orderLog.model.OrderLog;

@RestController
public class OrderLogRestController {

	@Autowired
	private OrderLogBO orderLogBO;
	
	@PostMapping("/stats_chart")
	public List<Map<String, Object>> statsChart(HttpSession session) {
		
		List<Map<String, Object>> statsMapList = new ArrayList<>();
		
		int userId = (int) session.getAttribute("userId");
		
		List<OrderLog> orderLogList = orderLogBO.getOrderLogList(userId);
		
		for (OrderLog orderLog : orderLogList) {
			Map<String, Object> statsMap = new HashMap<>();
			statsMap.put("itemName",orderLog.getItemName());
			statsMap.put("number", orderLog.getNumber());
			statsMap.put("price", orderLog.getPrice());
			statsMapList.add(statsMap);
		}
		
		return statsMapList;
	}
}
