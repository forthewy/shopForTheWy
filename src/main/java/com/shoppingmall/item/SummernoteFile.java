package com.shoppingmall.item;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

@Controller
public class SummernoteFile {

	public static final String FILE_UPLOAD_PATH = "D:\\Jane\\spring-project\\shop\\workspace\\images\\temp/";
			// 노트북 "D:\\Jane\\spring-project\\shop\\workspace\\images\\temp/";
			//"D:\\parkjeesoo\\shop\\workspace\\images\\temp/";

	
	
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(
			@RequestParam("file") MultipartFile multipartFile, 
			HttpServletRequest request )  {
		
		JsonObject jsonObject = new JsonObject();
		
		
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

		// 파일 저장 
		File targetFile = new File(FILE_UPLOAD_PATH + savedFileName + "/");

		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장

			jsonObject.addProperty("url", "/images/" + savedFileName); // 부를때 쓸 주소
			jsonObject.addProperty("responseCode", "success");

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		return a;
	}
}