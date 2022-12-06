package com.shoppingmall.item;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.item.bo.ItemBO;

@RequestMapping("/item")
@RestController
public class ItemRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ItemBO itemBO;
	
	/**
	 * 상품 등록
	 * @param name
	 * @param sort
	 * @param content
	 * @param price
	 * @param number
	 * @param deliveryPrice
	 * @param thumbnailImg
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> createItem(
			@RequestParam("name") String name,
			@RequestParam("sort") String sort,
			@RequestParam(value= "content", required=false) String content,
			@RequestParam("price") int price,
			@RequestParam("number") int number,
			@RequestParam("deliveryPrice") int deliveryPrice,
			@RequestParam("thumbnailImg") MultipartFile thumbnailImg,
			HttpSession session
			) {

		Map<String, Object> result = new HashMap<>();
		
		String sellerLoginId = (String) session.getAttribute("userLoginId");
		// 비로그인이라면 
		if (ObjectUtils.isEmpty(sellerLoginId)) {
			result.put("code", 550);
			result.put("errorMessage", "로그인되어있지 않습니다");
			return result;
		}		
		
		
		// DB insert
		int row = itemBO.addItem(sellerLoginId, name, number, price, content, sort, thumbnailImg, deliveryPrice);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("result", "상품 등록에 실패했습니다.");
			log.error("[상품 등록] 상품 등록 실패 sellerLoginId:{}", sellerLoginId);
		}
		
		return result;
	}
	
	/**
	 * 상품 수정
	 * @param name
	 * @param sort
	 * @param content
	 * @param price
	 * @param number
	 * @param deliveryPrice
	 * @param thumbnailImg
	 * @param itemId
	 * @param session
	 * @return
	 */
	
	@PostMapping("/update")
	public Map<String, Object> updateItem(
			@RequestParam("name") String name,
			@RequestParam("sort") String sort,
			@RequestParam(value= "content", required=false) String content,
			@RequestParam("price") int price,
			@RequestParam("number") int number,
			@RequestParam("deliveryPrice") int deliveryPrice,
			@RequestParam(value="thumbnailImg", required=false) MultipartFile thumbnailImg,
			@RequestParam("itemId") int itemId,
			HttpSession session
			) {

		Map<String, Object> result = new HashMap<>();
		
		String sellerLoginId = (String) session.getAttribute("userLoginId");
		
		// 비로그인이라면 
		if (ObjectUtils.isEmpty(sellerLoginId)) {
			result.put("code", 550);
			result.put("errorMessage", "로그인되어있지 않습니다");
			return result;
		}
		
		// DB update
		int row = itemBO.updateItem(sellerLoginId, name, number, price, content, sort, thumbnailImg, deliveryPrice, itemId);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
			log.info("[상품 수정] 상품 정보 수정 성공 itemId:{}", itemId);
		} else {
			result.put("code", 500);
			result.put("errorMessage", "상품 수정에 실패했습니다.");
			log.error("[상품 수정] 상품 정보 수정 실패 name:{}, number:{}, price:{}, content:{}, sort:{}, thumbnailImg:{},\"\r\n"
					+ "deliveryPrice:{}, itemId:{}, sellerLoginId:{}",  name, number, price, content, sort, thumbnailImg, deliveryPrice, itemId, sellerLoginId);
		}
		
		return result;
	}
	
	/**
	 * 상품 삭제
	 * @param itemId
	 * @param session
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> deleteItem(
			@RequestParam("itemId") int itemId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 비로그인이라면 
		if (ObjectUtils.isEmpty(userId)) {
			result.put("code", 550);
			result.put("errorMessage", "로그인되어있지 않습니다");
			return result;
		}
		
		
		//DB delete
		int row = itemBO.deleteItem(itemId);
				
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
			log.info("[상품 삭제] 상품 삭제 성공 itemId:{}", itemId); 
		} else {
			result.put("code", 500);
			result.put("errorMessage", "상품 삭제에 실패했습니다.");
			log.error("[상품 삭제] 상품 삭제 실패 itemId:{}", itemId); 
		}
				
		return result;
	}
	
}
