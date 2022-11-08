package com.shoppingmall.common;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	
	public static final String FILE_UPLOAD_PATH = "D:\\parkjeesoo\\shop\\workspace\\images/";
	
	// 사진 여러장 등록을 위해 변경
	public List<String> saveFile(String userLoginId, List<MultipartFile> fileList) {
		
		String directoryName = userLoginId + System.currentTimeMillis() + "/" ;
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if (directory == null) {
			return null;
		}
		
		for (MultipartFile fileName : fileList) {
			File 
		}
		 "/images/" + directoryName + file.getOriginalFilename();
		
		return ;
	}
	
	

}
}
