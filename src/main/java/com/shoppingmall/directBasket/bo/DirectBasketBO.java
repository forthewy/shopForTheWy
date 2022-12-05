package com.shoppingmall.directBasket.bo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.directBasket.dao.DirectBasketDAO;
import com.shoppingmall.directBasket.model.DirectBasket;
import com.shoppingmall.directBasket.model.DirectBasketItemView;
import com.shoppingmall.item.bo.ItemBO;
import com.shoppingmall.item.model.Item;

@Service
public class DirectBasketBO {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DirectBasketDAO directBasketDAO;
	
	@Autowired
	private ItemBO itemBO;
	
	// insert 하고 id 를 바로 받아온다.
	public Integer addDirectBasket(int userId, int itemId, int number) {
		
		// 같은 상품을 바로 주문 한적 있는 지 확인
		DirectBasket directBasket = getDirectBasketByUserIdAndItemId(userId, itemId);
		
		// 있다면 먼저 지운다.
		if (!ObjectUtils.isEmpty(directBasket)) {
			deleteDirectBasketById(directBasket.getId());
		}
		
		Map<String, Object> directBasketMap = new HashMap<>();
		directBasketMap.put("id", null);
		directBasketMap.put("userId", userId);
		directBasketMap.put("itemId", itemId);
		directBasketMap.put("number", number);
		
		directBasketDAO.insertDirectBasket(directBasketMap);
		
		return (Integer) directBasketMap.get("id");
	}
	
	public DirectBasket getDirectBasketById(int id) {
		return directBasketDAO.selectDirectBasketById(id);
	}
	
	public DirectBasket getDirectBasketByUserIdAndItemId (int userId, int itemId) {
		return directBasketDAO.selectDirectBasketByUserIdAndItemId(userId, itemId);
	}
	
	// 주문서 보여주기용
	public DirectBasketItemView generateDirectBasketItemView(int DirectBasketId,
			HttpServletResponse response) throws IOException {
		// 아이템 + 바로 주문 바구니 뷰
		DirectBasketItemView directBasketItemView = new DirectBasketItemView();
		
		// 바로 주문 바구니
		DirectBasket directBasket = getDirectBasketById(DirectBasketId);
		directBasketItemView.setDirectBasket(directBasket);
		
		// 아이템
		Item item = itemBO.getItemByItemId(directBasket.getItemId());
		// 상품 불러오기 실패시 주문 불가.
		if (ObjectUtils.isEmpty(item)) {
			log.error("[바로 주문] 바로 주문시 상품 불러오지 못함 userId:{},itemId:{}", directBasket.getUserId(), directBasket.getItemId());
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품을 불러오지 못했습니다'); history.go(-1);</script>");
			out.flush();
			return null;
		}
		directBasketItemView.setItem(item);
		
		return directBasketItemView;
	}
	
	public int deleteDirectBasketById(int Id) {
		
		int row = directBasketDAO.deleteDirectBasketById(Id);
		if (row > 0) {
			log.info("[바로 주문 삭제] 바로 주문 삭제 성공, directBasketId:{}", Id);
		} else if (row == 0) {
			log.warn("[바로 주문 삭제] 바로 주문 삭제 실패, directBasketId:{}", Id);
		}
		return row;
	}
}
