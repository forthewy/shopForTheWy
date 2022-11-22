package com.shoppingmall.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static final String FILE_UPLOAD_PATH = "D:\\parkjeesoo\\shop\\workspace\\images\\aaa/";
			// 노트북 주소 "D:\\Jane\\spring-project\\shop\\workspace\\images\\aaa/";
			// "D:\\parkjeesoo\\shop\\workspace\\images\\aaa/";
	
	public String saveFile(String userLoginId, MultipartFile file) {
		
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/" ;
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			return null; // 디렉토리 생성 실패시 null 리턴
		}
		

			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(filePath + file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
			return "/images/" + directoryName + file.getOriginalFilename();
	}
	
	public void deleteFile(String imgPath) {
		Path path = Paths.get(FILE_UPLOAD_PATH + imgPath.replace("/images/", ""));
		
		if (Files.exists(path)) {
			try {
				// 파일 삭제 성공 확인 완료
				Files.delete(path);
			} catch (IOException e) {
				log.error("[이미지 삭제] 이미지 삭제 실패 imgPath:{}", imgPath);
			}
		}
		
		path = path.getParent();
		if (Files.exists(path)) {
			try {
				// 폴더 삭제 성공 확인 완료
				Files.delete(path);
			} catch (IOException e) {
				log.error("[디렉토리 삭제] 디렉토리 삭제 실패 directoryPath:{}", path);
			}
		}
	}
}

