package com.shoppingmall.basket.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.basket.dao.BasketDAO;
import com.shoppingmall.basket.model.Basket;
import com.shoppingmall.basket.model.BasketItemView;
import com.shoppingmall.item.bo.ItemBO;
import com.shoppingmall.item.model.Item;

@Service
public class BasketBO {
	
	@Autowired
	private BasketDAO basketDAO;
	
	@Autowired
	private ItemBO itemBO;
	
	// 유저아이디로 장바구니 목록 가져오기
	public List<Basket> getBasketListByUserId(int userId) {
		return basketDAO.selectBasketListByUserId(userId);
	}
	
	// 장바구니 아이디 리스크로 장바구니 목록 가져오기
	public List<Basket> getBasketListByBasketIdList(List<Integer> basketIdList) {
		return basketDAO.selectBasketListByBasketIdList(basketIdList);
	}
	
	
	// 유저의 장바구니 화면에 아이템 정보 같이 가져오기
	public List<BasketItemView> generateBasketItemViewListByUserId(int userId) {
		List<BasketItemView> baskteItemViewList = new ArrayList<>();
		// 유저의 장바구니 목록을 가져온다.
		List<Basket> basketList = getBasketListByUserId(userId);
		
		for (Basket basket: basketList) {
			BasketItemView basketItemView = new BasketItemView();
			Item item = itemBO.getItemByItemId(basket.getItemId());
			
			basketItemView.setBasket(basket);
			basketItemView.setItem(item);
			
			baskteItemViewList.add(basketItemView);
		}
		return baskteItemViewList;
	}
	
	// 주문서용 장바구니 화면 구성
	public List<BasketItemView> generateBasketItemViewListBy(List<Integer> basketIdList) {
		List<BasketItemView> baskteItemViewList = new ArrayList<>();
		
		// 선택한 장바구니 목록을 가져온다.
		List<Basket> basketList = getBasketListByBasketIdList(basketIdList);
		
		for (Basket basket: basketList) {
			BasketItemView basketItemView = new BasketItemView();
			Item item = itemBO.getItemByItemId(basket.getItemId());
			
			basketItemView.setBasket(basket);
			basketItemView.setItem(item);
			
			baskteItemViewList.add(basketItemView);
		}
		return baskteItemViewList;
	}
	
	
	// 장바구니 넣기
	public int addBasket(int userId, int itemId, int number) {
		
		Basket basket = getBasketByUserIdAndItemId(userId, itemId);
		int row = 0;
		
		// 담은 적없는 상품이라면 그대로 넣는다.
		if (ObjectUtils.isEmpty(basket)) {
			row = basketDAO.insertBasket(userId, itemId, number);
		} else {
			// 담은 적있는 상품이라면 갯수만 추가한다.
			row = updateBasket(userId, itemId, number + basket.getNumber());
		}
		
		return row;
	}
	
	public Basket getBasketByUserIdAndItemId(int userId, int itemId) {
		return basketDAO.existBasketByUserIdAndItemId(userId, itemId);
	}
	
	// 장바구니 삭제
	public int deleteBasket(int id) {
		return basketDAO.deleteBasket(id); 
	}
	
	// 장바구니 수정
	public int updateBasket(int userId, int itemId, int number) {
		return basketDAO.updateBasket(userId, itemId, number);
	}

}
