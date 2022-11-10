package com.shoppingmall.seller.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.seller.dao.SellerDAO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.user.bo.UserBO;

@Service
public class SellerBO {

	@Autowired
	private SellerDAO sellerDAO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public Seller getSellerByUserLoginId(String loginId) {
		int userId = userBO.getIdByLoginId(loginId);
		
		return getSellerByUserId(userId);
	}
	
	public Seller getSellerByUserId(int userId) {
		return sellerDAO.selectSellerByUserId(userId);
	}
	
	// 상점 정보 수정
	public int updateSellerByUserId(int userId, String userLoginId, String shopName, String address,
			String shopPhoneNumber, MultipartFile bannerImg, MultipartFile shopMainImg) {
		
		Seller seller = getSellerByUserId(userId);
		
		// 배너이미지가 비어있지 않다면
		String bannerImgPath = null;
		if (!ObjectUtils.isEmpty(bannerImg)) {
			bannerImgPath = fileManagerService.saveFile(userLoginId, bannerImg);
			
			if (bannerImgPath != null && seller.getBannerImg() != null) {
				fileManagerService.deleteFile(seller.getBannerImg());
			}
		}
		
		// 상점 이미지가 비어있지 않다면
		String shopMainImgPath = null;
		if (!ObjectUtils.isEmpty(shopMainImg)) {
			shopMainImgPath = fileManagerService.saveFile(userLoginId, shopMainImg);
					
			if (shopMainImgPath != null && seller.getShopMainImg() != null) {
				fileManagerService.deleteFile(seller.getShopMainImg());
			}
		}
		
		return sellerDAO.updateSellerByUserId(userId, shopName, address,
				shopPhoneNumber, bannerImgPath, shopMainImgPath);
	}
}
