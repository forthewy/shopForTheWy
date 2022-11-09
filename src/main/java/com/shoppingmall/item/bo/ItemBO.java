package com.shoppingmall.item.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.item.dao.ItemDAO;

@Service
public class ItemBO {
	
	@Autowired
	private FileManagerService fileManagerService;

	@Autowired
	private ItemDAO itemDAO;
	
	public int addItem(int sellerId, String sellerLoginId, String name, int number, int price, String content, String sort, MultipartFile thumbnailImg, int deliveryPrice) {
		String imagePath = null;
		
		imagePath = fileManagerService.saveFile(sellerLoginId, thumbnailImg);
		
		return itemDAO.insertItem(sellerId, name, number, price, content, sort, imagePath, deliveryPrice);
	};
	
}
