package com.shoppingmall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shoppingmall.common.FileManagerService;
import com.shoppingmall.interceptor.PermissionInterceptor;
import com.shoppingmall.item.SummernoteFile;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private PermissionInterceptor interceptor;
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/images/**")
	                .addResourceLocations("file:///" + SummernoteFile.FILE_UPLOAD_PATH)
	                .addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);
			       
	    }
	 
	 @Override
	 	public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(interceptor)
		 		 .addPathPatterns("/**")
		 		 .excludePathPatterns("/favicon.ico, /error, /user/sign_out, /static/**");
		 		 
	 }
}
