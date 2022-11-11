package com.shoppingmall.item.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.item.dao.ItemDAO;
import com.shoppingmall.item.model.Item;
import com.shoppingmall.seller.bo.SellerBO;
import com.shoppingmall.seller.model.Seller;

@Service
public class ItemBO {
	
	@Autowired
	private FileManagerService fileManagerService;

	@Autowired
	private SellerBO sellerBO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	// 상품 등록
	public int addItem(String sellerLoginId, String name, int number, int price, String content, String sort, MultipartFile thumbnailImg, int deliveryPrice) {
		String imagePath = null;
		
		Seller seller = sellerBO.getSellerByUserLoginId(sellerLoginId);
		int sellerId = seller.getId();
		
		// 사진 등록
		imagePath = fileManagerService.saveFile(sellerLoginId, thumbnailImg);
		
		return itemDAO.insertItem(sellerId, name, number, price, content, sort, imagePath, deliveryPrice);
	};
	
	// 상품 리스트
	public List<Item> getItemBySellerId(int sellerId) {
		return itemDAO.selectItemBySellerId(sellerId);
	}
	
	// 상품 아이디로 상품 조회
	public Item getItemByItemId(int itemId) {
		return itemDAO.selectItemByItemId(itemId);
	}
}
