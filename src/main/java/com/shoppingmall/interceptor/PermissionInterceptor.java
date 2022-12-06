package com.shoppingmall.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws IOException {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		Integer userType = (Integer) session.getAttribute("userType");
		
		// 요청 url
		String uri = request.getRequestURI();
		log.info("[####### prehandle uri:{}", uri);
		
		// 비로그인 && bookmark로 온경우 ==> 로그인 페이지로 redirect
		if (userName == null && uri.startsWith("/bookmark")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		// seller로 온경우. 상점 신청 제외. 
		if (uri.startsWith("/seller") && !uri.startsWith("/seller/create")) {
			// 비로그인이라면 ==> 로그인 페이지로 redirect
			if (userType == null) {
				response.sendRedirect("/user/sign_in_view");
				return false;
			// 일반 유저라면 ==> 권한이 없다고 alert 이후 history.go(-1) 되돌아간다.
			} else if (userType < 2) {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('상점만 이용가능한 메뉴입니다.'); history.go(-1);</script>");
	            out.flush(); 
				return false;
			}
		}
		
		// 아이템 등록 화면에 일반 유저가 온 경우
		if (uri.startsWith("/item/item_create_view") && userType < 2) {
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('상점만 이용가능한 메뉴입니다.'); history.go(-1);</script>");
            out.flush(); 
			return false;
		}
		
		// 비로그인 && basket로 온경우 ==> 로그인 페이지로 redirect
		if (userName == null && uri.startsWith("/basket")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		// 비로그인 && 바로 주문으로 온경우 ==> 로그인 페이지로 redirect
		if (userName == null && uri.startsWith("/direct_basket")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		// 비로그인 && 문의 목록으로 온경우 ==> 로그인 페이지로 redirect
		if (userName == null && uri.startsWith("/chatroom")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		// 비로그인 && 문의로 온경우 ==> 로그인 페이지로 redirect
		if (userName == null && uri.startsWith("/message")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		// 비로그인 && 리뷰로 온경우 ==> 로그인 페이지로 redirect
		if (userName == null && uri.startsWith("/review")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object Handler, ModelAndView modelAndView) {

		log.info("[######## postHandle]");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object Handler, Exception ex) {
		log.info("[######## afterCompletion]");
	}
}
