package com.shoppingmall.seller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.seller.bo.SellerBO;

@RequestMapping("/seller")
@RestController
public class SellerRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SellerBO sellerBO;
	
	/**
	 * 상점 정보 수정
	 * @param shopName
	 * @param address
	 * @param shopPhoneNumber
	 * @param bannerImg
	 * @param shopMainImg
	 * @param session
	 * @return
	 */
	@PostMapping("/update")
	public Map<String, Object> update(
			@RequestParam(value="shopName" , required=false) String shopName,
			@RequestParam(value="address" , required=false) String address,
			@RequestParam(value="shopPhoneNumber" , required=false) String shopPhoneNumber,
			@RequestParam(value="bannerImg", required=false) MultipartFile bannerImg,
			@RequestParam(value="shopMainImg", required=false) MultipartFile shopMainImg,
			HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		String userLoginId = (String) session.getAttribute("userLoginId");
		
		
		int row = sellerBO.updateSellerByUserId(userId, userLoginId,  shopName, address, shopPhoneNumber, bannerImg, shopMainImg);
		
		
		Map<String, Object> result = new HashMap<>();
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
			log.info("[상점 수정] 상점 수정 성공 userId:{}, shopName:{}, address:{}, shopPhoneNumber:{}", userId, shopName, address, shopPhoneNumber);
		} else {
			result.put("code", 500);
			result.put("errorMessage", "상점 정보 수정에 실패했습니다");
			log.error("[상점 수정] 상점 수정 실패 userId:{}, shopName:{}, address:{}, shopPhoneNumber:{}", userId, shopName, address, shopPhoneNumber);
		}
		
		return result;
	}
	
	
	/**
	 * 상점 신청
	 * @param shopName
	 * @param address
	 * @param shopPhoneNumber
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam(value="shopName") String shopName,
			@RequestParam(value="address") String address,
			@RequestParam(value="shopPhoneNumber") String shopPhoneNumber,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		int userId = (int) session.getAttribute("userId");
		
		int row = sellerBO.addSeller(userId, shopName, address, shopPhoneNumber);
		
		if (row > 0) {
			result.put("code", 300);
			result.put("result", "success");
			log.info("[상점 신청] 상점 신청 성공 userId:{}, shopName:{}, shopPhoneNumber:{}", userId, shopName, shopPhoneNumber);
		} else {
			result.put("code", 500);
			result.put("errorMessage", "상점 신청에 실패했습니다");
			log.error("[상점 신청] 상점 신청 실패 userId:{}, shopName:{}, shopPhoneNumber:{}", userId, shopName, shopPhoneNumber);
		}
		
		return result;
	}
}
