<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-100 d-flex justify-content-center">
	<div class="w-75 box">
 		<c:choose>
	 		<c:when test="${empty bookmarkViewList}">
	 			<div class="d-flex justify-content-center align-items-center h-100">
					<div class="display-4">즐겨찾기 한 상점이 없습니다.</div>
				</div>
	 		</c:when>
	 		<c:otherwise>
		 		<div class="d-flex flex-nowrap">
			 		<c:forEach items="${bookmarkViewList}" var="bookmarkView">
			 			<div class="bookmark-box m-3 border d-flex justify-content-center align-items-center">
			 				<a href="/shop/shop_view/${bookmarkView.sellerUserLoginId}">
				 				<img alt="상점 메인 이미지" src="${bookmarkView.seller.shopMainImg}" width="200px" height="200px">
				 				<h1>${bookmarkView.seller.shopName}</h1>
			 				</a>
			 			</div>
			 		</c:forEach>
		 		</div>
	 		</c:otherwise>
 		</c:choose>
	</div>
</div>
