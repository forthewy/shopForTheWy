package com.shoppingmall.basketOrder.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.shoppingmall.order.model.Order;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@Service
public class BasketOrderBO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BasketOrderDAO basketOrderDAO;

	@Autowired
	private OrderBO orderBO;

	@Autowired
	private DirectBasketBO directBasketBO;

	@Autowired
	private BasketBO basketBO;

	@Autowired
	private SellerBO sellerBO;

	@Autowired
	private ItemBO itemBO;

	// 주문 하기
	public int addBasketOrder(int userId, String phoneNumber, String address, String name
			, Integer directBasketId, Integer directPrice, List<String> basketIdAndEachTotalPriceList) {

		int row = 0;

		// 먼저 order에 등록해서 등록할 orderId를 얻는다. (바로 주문, 장바구니 주문 공통)
		int orderId = orderBO.addOrder(userId, phoneNumber, address, name);

		// 바로 주문시
		if (!ObjectUtils.isEmpty(directBasketId)) {
			DirectBasket directBasket = directBasketBO.getDirectBasketById(directBasketId);

			directBasketBO.deleteDirectBasketById(directBasket.getId());
			
			row = basketOrderDAO.insertDirectBasketOrder(orderId, directBasket.getItemId(), directBasket.getNumber(),
					directPrice);
			if (row > 1) {
				log.error("[바로 주문] 바로 주문시 중복 주문 발생 orderId:{}", orderId);
			}

		} else {
			// 장바구니 주문시
			List<Map<String, Integer>> basketMapList = new ArrayList<>();

			for (String basketIdAndEachTotalPrice : basketIdAndEachTotalPriceList) {
				Map<String, Integer> basketMap = new HashMap<>();
				// basketId / price
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
			
			if (row > basketMapList.size()) {
				log.error("[장바구니 주문] 장바구니 주문시 중복 주문 발생 orderId:{}", orderId);
			} else if (row < basketMapList.size()){
				log.error("[장바구니 주문] 장바구니 주문시 미주문건 발생 orderId:{}", orderId);
			}
		}

		return row;
	}

	public List<BasketOrder> getBasketOrderByOrderIdList(List<Integer> orderIdList) {
		return basketOrderDAO.selectBasketOrderByOrderIdList(orderIdList);
	}

	// 유저Id 로 주문조회 하기
	public List<BasketOrderView> getBasketOrderViewListByUserId(int userId) {
		List<BasketOrderView> basketOrderViewList = new ArrayList<>();

		// orderId 목록을 가져온다
		List<Integer> orderIdList = orderBO.getOrderIdListByUserId(userId);

		// orderId 목록이 비어있지 않다면
		if (!ObjectUtils.isEmpty(orderIdList)) {
			List<BasketOrder> basketOrderList = getBasketOrderByOrderIdList(orderIdList);

			for (BasketOrder basketOrder : basketOrderList) {
				BasketOrderView basketOrderView = new BasketOrderView();
				basketOrderView.setBasketOrder(basketOrder);

				// item 정보를 가져온다.
				Item item = itemBO.getItemByItemId(basketOrder.getItemId());
				basketOrderView.setItem(item);

				// order 정보를 가져온다.
				Order order = orderBO.getOrderById(basketOrder.getOrderId());
				basketOrderView.setOrder(order);

				basketOrderViewList.add(basketOrderView);
			}
		}
		return basketOrderViewList;
	}

	// seller의 유저아이디와 구매한 사람 이름으로 조회하기
	  public List<BasketOrderView> getBasketOrderViewListBySellerUserIdAndSearchName(int userId, String searchName, String searchState) {
		  List<BasketOrderView> basketOrderViewList = new ArrayList<>();
		  
		  // 상점 가져오기 
		  Seller seller = sellerBO.getSellerByUserId(userId);
		  
		  // 상품아이디 목록 가져오기 
		  List<Integer> itemList = itemBO.getItemIdListBySellerId(seller.getId());
		  
		  // 주문 목록 가져오기 
		  List<BasketOrder> basketOrderList = new ArrayList<>();
		  if (ObjectUtils.isEmpty(searchState)) {
			  // 1) 상태 검색이 선택되지 않았을때
			  basketOrderList	 = getBasketOrderByItemIdList(itemList);
		  } else {
			  // 2) 상태 검색이 선택되었을때			  
			  basketOrderList	 = getBasketOrderByItemIdListAndState(itemList, searchState);
			  if (ObjectUtils.isEmpty(basketOrderList)) {
				  return null;
			  }
		  }
		  
		  
		  for (BasketOrder basketOrder : basketOrderList) {
			  BasketOrderView basketOrderView = new BasketOrderView();
			  // basketOrder 넣기 
			  basketOrderView.setBasketOrder(basketOrder);
			  
			  // order 불러오기
			  	// 1) 검색어가 없을 경우
			  if (ObjectUtils.isEmpty(searchName)) {
				  Order order = orderBO.getOrderById(basketOrder.getOrderId());
				  basketOrderView.setOrder(order);
			  } else {
				  // 2) 검색어(이름)가 있을 경우
				  Order order = orderBO.getOrderByIdAndName(basketOrder.getOrderId(), searchName);
				  if (ObjectUtils.isEmpty(order)) {
					  continue;
				  } else {
					  basketOrderView.setOrder(order); 
				  }
			  }
			  
			  
			  // item 정보 넣기
			  Item item = itemBO.getItemByItemId(basketOrder.getItemId());
			  basketOrderView.setItem(item);
			  
			  basketOrderViewList.add(basketOrderView);
		 }
		 
		 return basketOrderViewList;
	 
	 }
	 

	// 아이템 아이디 리스트로 주문 목록 가져오기
	public List<BasketOrder> getBasketOrderByItemIdList(List<Integer> itemIdList) {
		return basketOrderDAO.selectBasketOrderByItemIdList(itemIdList);
	}
	
	// 주문 상태 변경
	public int updateBasketOrder(int id, String state) {
		return basketOrderDAO.updateBasketOrder(id, state);
	}
	
	public List<BasketOrder> getBasketOrderByItemIdListAndState(List<Integer> itemIdList, String state) {
		return basketOrderDAO.selectBasketOrderByItemIdListAndState(itemIdList, state);
	}

}
