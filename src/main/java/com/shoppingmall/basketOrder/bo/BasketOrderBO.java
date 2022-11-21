package com.shoppingmall.basketOrder.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.basket.bo.BasketBO;
import com.shoppingmall.basket.model.Basket;
import com.shoppingmall.basketOrder.dao.BasketOrderDAO;
import com.shoppingmall.basketOrder.model.BasketOrder;
import com.shoppingmall.basketOrder.model.BasketOrderView;
import com.shoppingmall.directBasket.bo.DirectBasketBO;
import com.shoppingmall.directBasket.model.DirectBasket;
import com.shoppingmall.item.bo.ItemBO;
import com.shoppingmall.item.model.Item;
import com.shoppingmall.order.bo.OrderBO;

@Service
public class BasketOrderBO {

	@Autowired
	private BasketOrderDAO basketOrderDAO;
	
	@Autowired
	private OrderBO orderBO;

	@Autowired
	private DirectBasketBO directBasketBO;
	
	@Autowired
	private BasketBO basketBO;
	
	@Autowired
	private ItemBO itemBO;
	
	// 주문 하기
	public int addBasketOrder(int userId, String phoneNumber, String address,
			Integer directBasketId, Integer directPrice, List<String> basketIdAndEachTotalPriceList) {
		
		int row = 0;
		
		// 먼저 order에 등록해서 등록할 orderId를 얻는다. (바로 주문, 장바구니 주문 공통)
		int orderId = orderBO.addOrder(userId, phoneNumber, address);
		
		// 바로 주문시
		if (!ObjectUtils.isEmpty(directBasketId)) {
			DirectBasket directBasket = directBasketBO.getDirectBasketById(directBasketId);
			
			directBasketBO.deleteDirectBasketById(directBasket.getId());
			
			row = basketOrderDAO.insertDirectBasketOrder(orderId,  directBasket.getItemId(), directBasket.getNumber(), directPrice);
		
		} else {
		// 장바구니 주문시
			List<Map<String, Integer>> basketMapList = new ArrayList<>();
			
			for (String basketIdAndEachTotalPrice : basketIdAndEachTotalPriceList) {
				Map<String, Integer> basketMap = new HashMap<>();
				//  basketId / price
				String[] basketIdAndEachTotalPriceArr = basketIdAndEachTotalPrice.split("/");
				
				Basket basket = basketBO.getBasketById(Integer.parseInt(basketIdAndEachTotalPriceArr[0]));
				
				basketMap.put("itemId", basket.getItemId());
				basketMap.put("number", basket.getNumber());
				basketMap.put("price", Integer.parseInt(basketIdAndEachTotalPriceArr[1]));
				basketMapList.add(basketMap);
				
				// 장바구니 삭제
				basketBO.deleteBasket(basket.getId());
			}
			
			row = basketOrderDAO.insertBasketOrder(orderId, basketMapList);
			
			
		}
		
		return row;
	}
	
	// 유저Id 로 주문조회 하기
	public List<BasketOrderView> getBasketOrderViewListByUserId(int userId) {
		List<BasketOrderView> BasketOrderViewList = new ArrayList<>();
		
		// orderId 목록을 가져온다
		List<Integer> orderIdList =  orderBO.getOrderIdListByUserId(userId);
		
		// BasketOrder 목록 
		List<BasketOrder> basketOrderList = getBasketOrderByOrderIdList(orderIdList);
		for (BasketOrder basketOrder: basketOrderList) {
			BasketOrderView basketOrderView = new BasketOrderView();
			basketOrderView.setBasketOrder(basketOrder);
			
			// item 정보를 가져온다.
			Item item = itemBO.getItemByItemId(basketOrder.getItemId());
			basketOrderView.setItem(item);
			
			BasketOrderViewList.add(basketOrderView);
		}
		
		return BasketOrderViewList;
	}
	
	public List<BasketOrder> getBasketOrderByOrderIdList(List<Integer> orderIdList){
		return basketOrderDAO.selectBasketOrderByOrderIdList(orderIdList);
	}
}
