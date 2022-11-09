<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 배너 이미지 --%>
<div id="banner" class="bg-success">
	<img src="${shop.seller.bannerImg}" alt="배너이미지" width="100%" height="100%"> 
</div>
<%-- 버튼들 --%>
<div class="mt-3 d-flex justify-content-end mb-5">
	<c:choose>
		<c:when test="${userId ne shop.seller.userId}">
			<button class="ml-4 btn btn-info">이 상점을 즐겨 찾기</button>
		</c:when>
		<c:otherwise>
			<div class="mr-4">
				<button class="btn btn-secondary" onClick="location.href='/item/item_create_view'">상품 등록하기</button>
				<button class="mr-3 btn btn-dark" onClick="location.href='/seller/update_view'">설정</button>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<%-- 상품들 --%>
<div class="d-flex justify-content-center">
	<div class="item-box d-flex flex-wrap justify-content-start">
		<c:forEach items="${shop.itemList}" var="item">
			<div class="mr-5 mb-4">
				<a href="#">
					<img alt="상품 썸네일 이미지" src="${item.thumbnailImg}" width="500px" height="300px">
					<h3>${item.name}</h3>
					<h4><b>${item.price}원</b></h4>
				</a>
			</div>
		</c:forEach>
	</div>
</div>