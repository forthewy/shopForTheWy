package com.shoppingmall.order;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppingmall.directBasket.bo.DirectBasketBO;
import com.shoppingmall.directBasket.model.DirectBasket;
import com.shoppingmall.order.bo.OrderViewBO;
import com.shoppingmall.order.model.OrderView;

@RequestMapping("/order")
@Controller
public class OrderViewController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderViewBO orderViewBO;
	
	@Autowired
	private DirectBasketBO directBasketBO;
	
	/**
	 * 주문서 화면
	 * @param directBasketId
	 * @param basketIdList
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/order_create_view")
	public String orderCreateView(
			@RequestParam(value="directBasketId", required=false) Integer directBasketId,
			@RequestParam(value="check", required=false) List<Integer> basketIdList,
			HttpServletResponse response,
			HttpSession session,
			Model model) throws IOException {
		
		int userId = (int) session.getAttribute("userId");
		
		if (!ObjectUtils.isEmpty(directBasketId)) {
			DirectBasket directBasket = directBasketBO.getDirectBasketById(directBasketId);
			if (directBasket.getUserId() != userId) {
				log.error("[주문서] 바로 주문 바구니 유저와 세션이 다름. userId", directBasket.getUserId(), directBasket.getItemId());
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('바로 주문 상품을 불러오지 못했습니다'); history.go(-1);</script>");
	            out.flush(); 
	            return null;
			}
		}
		
		OrderView orderView = orderViewBO.generateOrderView(directBasketId, basketIdList, response);
		
		model.addAttribute("orderView", orderView);
		model.addAttribute("viewName", "order/orderCreate");
		
		return "template/layout";
	}
	
	
}
