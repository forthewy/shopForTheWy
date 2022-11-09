package com.shoppingmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.item.SummernoteFile;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/images/**")
	                .addResourceLocations("file:///" + SummernoteFile.FILE_UPLOAD_PATH)
	                .addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);
			       
	    }
}
