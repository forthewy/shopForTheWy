package com.shoppingmall.item.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.item.dao.ItemDAO;
import com.shoppingmall.item.model.Item;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;
import com.shoppingmall.user.bo.UserBO;

@Service
public class ItemBO {
	
	@Autowired
	private FileManagerService fileManagerService;

	@Autowired
	private SellerBO sellerBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	// 상품 등록
	public int addItem(String sellerLoginId, String name, int number, int price, String content, String sort
			, MultipartFile thumbnailImg, int deliveryPrice) {
		String imagePath = null;
		
		Seller seller = sellerBO.getSellerByUserLoginId(sellerLoginId);
		int sellerId = seller.getId();
		
		// 사진 등록
		imagePath = fileManagerService.saveFile(sellerLoginId, thumbnailImg);
		
		return itemDAO.insertItem(sellerId, name, number, price, content, sort, imagePath, deliveryPrice);
	};
	
	// 상품 수정
	public int updateItem(String sellerLoginId, String name, int number, int price, String content, String sort
			, MultipartFile thumbnailImg, int deliveryPrice, int itemId) {
		
		// 수정할 아이템을 가져온다.
		Item item = getItemByItemId(itemId);
		
		
		// 썸네일 이미지가 있다면
		String imagePath = null;
		Seller seller = sellerBO.getSellerByUserLoginId(sellerLoginId);
		int sellerId = seller.getId();

		// 기존 이미지 삭제 후 새로운 사진 저장 테스트 완료
		if (!ObjectUtils.isEmpty(thumbnailImg)) {
			// 기존 사진은 지우고
			fileManagerService.deleteFile(item.getThumbnailImg());
			// 새로운 사진 저장
			imagePath = fileManagerService.saveFile(sellerLoginId, thumbnailImg);
		}
		
		return itemDAO.updateItem(name, number, price, content, sort, imagePath, deliveryPrice, itemId);
	};
	
	public int deleteItem(int itemId) {
		
		// 삭제할 상품을 가져온다.
		Item item = getItemByItemId(itemId);
		
		// 이미지를 삭제한다.
		fileManagerService.deleteFile(item.getThumbnailImg());
		
		return itemDAO.deleteItem(itemId);
	}
	
	// 상점의 상품 전체 리스트
	public List<Item> getItemBySellerId(int sellerId) {
		return itemDAO.selectItemBySellerId(sellerId);
	}
	
	// 상점의 상품Id 리스트
	public List<Integer> getItemIdListBySellerId(int sellerId) {
		return itemDAO.selectItemIdListBySellerId(sellerId);
	}
	
	// 페이지로 상품 일부만 조회
	public List<Item> getItemBySellerIdLimitPage(int sellerId, int page) {
		int offsetNum = 3 * (page - 1); // 조회시 건너뛰고 조회해야할 행 갯수 0 3 6...
		return itemDAO.selectItemBySellerIdLimitPage(sellerId, offsetNum);
	}
	
	// 상품 아이디로 상품 조회
	public Item getItemByItemId(int itemId) {
		return itemDAO.selectItemByItemId(itemId);
	}
	
	// 한 상점의 상품 총 갯수 확인
	public int getItemCountBySellerId(int sellerId) {
		return itemDAO.selectItemCountBySellerId(sellerId);
	}
	
}
