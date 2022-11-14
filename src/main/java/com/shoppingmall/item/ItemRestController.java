package com.shoppingmall.item;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.item.bo.ItemBO;

@RequestMapping("/item")
@RestController
public class ItemRestController {

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
		
		// DB insert
		int row = itemBO.addItem(sellerLoginId, name, number, price, content, sort, thumbnailImg, deliveryPrice);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("result", "상품 등록에 실패했습니다.");
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
	
	@PutMapping("/update")
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
		
		// DB update
		int row = itemBO.updateItem(sellerLoginId, name, number, price, content, sort, thumbnailImg, deliveryPrice, itemId);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "상품 수정에 실패했습니다.");
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
		
		//DB delete
		int row = itemBO.deleteItem(itemId);
				
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "상품 삭제에 실패했습니다.");
		}
				
		return result;
	}
	
}
