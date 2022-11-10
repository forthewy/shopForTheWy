	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 배너 이미지 --%>
<div class="d-flex justify-content-center">
	<div id="banner" class="bg-success">
		<img src="${shop.seller.bannerImg}" alt="배너이미지" width="100%" height="100%"> 
	</div>
</div>
<%-- 버튼들 --%>
<div class="mt-3 d-flex justify-content-end mr-5 mb-5">
	<c:choose>
		<c:when test="${userId ne shop.seller.userId}">
			<div class="mr-4">
				<%-- <c:choose>
					<c:when test="${shop.isBookmarked}">
						<button class="like-btn mr-3 btn  btn-outline-info" data-seller-id="${shop.seller.id}">즐겨찾기 취소</button>
					</c:when>
					<c:otherwise>
						<button class="like-btn mr-3 btn btn-info" data-seller-id="${shop.seller.id}">이 상점을 즐겨 찾기</button>
					</c:otherwise>
				</c:choose> --%>
			</div>
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
				<a href="/item/item_detail_view?itemId=${item.id}">
					<img alt="상품 썸네일 이미지" src="${item.thumbnailImg}" width="500px" height="300px">
					<h3>${item.name}</h3>
					<h4><b>${item.price}원</b></h4>
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		<%-- 즐겨찾기 버튼 클릭 --%>
		$('.like-btn').on('click', function() {
			let sellerId = $(this).data('seller-id');
			$.ajax({
				url: "/bookmark/" + sellerId
				, success:function(data) {
					if (data.code == 300) {
						alert('성공');
					} else {
						alert('실패!');
					}
				}
				, error:function(e) {
					alert("즐겨찾기 등록에 실패했습니다. 관리자에게 문의바랍니다");
				}
			});
		});
	});
</script>