package com.shoppingmall.basket.bo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.basket.dao.BasketDAO;
import com.shoppingmall.basket.model.Basket;
import com.shoppingmall.basket.model.BasketItemView;
import com.shoppingmall.item.bo.ItemBO;
import com.shoppingmall.item.model.Item;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@Service
public class BasketBO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BasketDAO basketDAO;
	
	@Autowired
	private ItemBO itemBO;
	
	@Autowired
	private SellerBO sellerBO;
	
	// 유저아이디로 장바구니 목록 가져오기
	public List<Basket> getBasketListByUserId(int userId) {
		return basketDAO.selectBasketListByUserId(userId);
	}
	
	// 장바구니 아이디 리스크로 장바구니 목록 가져오기
	public List<Basket> getBasketListByBasketIdList(List<Integer> basketIdList) {
		return basketDAO.selectBasketListByBasketIdList(basketIdList);
	}
	
	
	// 장바구니 아이디로 장바구니 가져오기
	public Basket getBasketById(int id) {
		return basketDAO.selectBasketById(id);
	}
	
	// 유저의 장바구니 화면에 아이템 정보 같이 가져오기
	public List<BasketItemView> generateBasketItemViewListByUserId(int userId) {
		List<BasketItemView> baskteItemViewList = new ArrayList<>();
		// 유저의 장바구니 목록을 가져온다.
		List<Basket> basketList = getBasketListByUserId(userId);
		
		for (Basket basket: basketList) {
			BasketItemView basketItemView = new BasketItemView();
			Item item = itemBO.getItemByItemId(basket.getItemId());
			basketItemView.setItem(item);

			if (!ObjectUtils.isEmpty(item)) {
				Seller seller = sellerBO.getSellerById(item.getSellerId());
				basketItemView.setSellerShopName(seller.getShopName());
			}
			
			basketItemView.setBasket(basket);
			baskteItemViewList.add(basketItemView);
		}
		return baskteItemViewList;
	}
	
	// 장바구니 주문서용 화면 구성
	public List<BasketItemView> generateBasketItemViewListByBasketIdList(List<Integer> basketIdList
			, HttpServletResponse response) throws IOException {
		List<BasketItemView> baskteItemViewList = new ArrayList<>();
		
		// 선택한 장바구니 목록을 가져온다.
		List<Basket> basketList = getBasketListByBasketIdList(basketIdList);
		
		for (Basket basket: basketList) {
			BasketItemView basketItemView = new BasketItemView();
			Item item = itemBO.getItemByItemId(basket.getItemId());
			// 상품 불러오기 실패시 주문 불가.
			if (ObjectUtils.isEmpty(item)) {
				log.error("[장바구니 주문] 장바구니 주문시 상품 불러오지 못함 userId:{},itemId:{}", basket.getUserId(), basket.getItemId());
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('장바구니 상품을 불러오지 못했습니다'); history.go(-1);</script>");
	            out.flush(); 
				break;
			}
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
	
	// userId와 itemId로 담은적 있는 상품인지 확인
	public Basket getBasketByUserIdAndItemId(int userId, int itemId) {
		return basketDAO.existBasketByUserIdAndItemId(userId, itemId);
	}
	
	// 장바구니 삭제
	public int deleteBasket(int id) {
		int row =  basketDAO.deleteBasket(id); 
		if (row > 0) {
			log.info("[장바구니 삭제] 장바구니 삭제 성공 basketId:{}", id);
		} else {
			log.error("[장바구니 삭제] 장바구니 삭제 실패 basketId:{}", id);
		}
		return row;
	}
	
	// 장바구니 담은 갯수 수정
	public int updateBasket(int userId, int itemId, int number) {
		int row = basketDAO.updateBasket(userId, itemId, number);
		
		if (row > 0) {
			log.info("[장바구니 수정] 장바구니 수정 성공 userId:{}, itemId:{}, number:{}", userId, itemId, number);
		} else {
			log.error("[장바구니 수정] 장바구니 수정 실패 userId:{}, itemId:{}, number:{}", userId, itemId, number);
		}
		return row;
	}

}
