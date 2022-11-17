package com.shoppingmall.directBasket.bo;

import java.util.HashMap;
import java.util.Map;

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
	public DirectBasketItemView generateDirectBasketItemView(int DirectBasketId) {
		// 아이템 + 바로 주문 바구니 뷰
		DirectBasketItemView directBasketItemView = new DirectBasketItemView();
		
		// 바로 주문 바구니
		DirectBasket directBasket = getDirectBasketById(DirectBasketId);
		directBasketItemView.setDirectBasket(directBasket);
		
		// 아이템
		Item item = itemBO.getItemByItemId(directBasket.getItemId());
		directBasketItemView.setItem(item);
		
		return directBasketItemView;
	}
	
	public int deleteDirectBasketById(int basketId) {
		return directBasketDAO.deleteDirectBasketById(basketId);
	}
}
