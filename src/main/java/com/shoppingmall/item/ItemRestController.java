package com.shoppingmall.item;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

@RequestMapping("/item")
@Controller
public class ItemRestController {

	@RequestMapping("/create")
	public Map<String, Object> createItem(
			@RequestParam ("name") String name,
			@RequestParam("price") int price,
			@RequestParam("number") int  number,
			@RequestParam(value="content", required=false) String content,
			@RequestParam("sortId") int sortId,
			@RequestParam("thumbnailImg") String thumbnailImg,
			@RequestParam("deliveryPrice") int deliveryPrice,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}
	
}
