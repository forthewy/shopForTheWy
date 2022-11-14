<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="item-detail-box d-flex">
			<div class="w-50 d-flex justify-content-center align-items-center">
				<img alt="썸네일이미지" src="${itemDetailView.item.thumbnailImg}" width="80%" height="80%">
			</div>
			<div class="w-50">
				<div class="ml-3 pt-5 d-flex justify-content-between">
					<h1>${itemDetailView.item.name}</h1>
					<h1 class="text-success mr-5"><fmt:formatNumber value="${itemDetailView.item.price}" type="number"/> 원</h1>
				</div>
				<hr>
				<div class="d-flex justify-content-end mr-5">
					<h2>배송비 <fmt:formatNumber value="${itemDetailView.item.deliveryPrice}" type="number"/> 원</h2>
				</div>
				<hr>
				<div>
					<c:choose>
						<%-- 상품을 올린 상점과 유저 일치 여부에 따라 보이는 버튼 --%>
						<c:when test="${!itemDetailView.isUserSeller}">
							<div class="d-flex justify-content-end input-group mr-5">
								<div class="input-group-prepend">
									<button class="count-btn btn btn-secondary" id="minusBtn"><b>-</b></button>
								</div>
								<input type="text" id="buyCount" value="1" class="form-control col-1 text-center">
								<div class="input-group-append">
									<button class="count-btn mr-5 btn btn-secondary" id="plusBtn"><b>+</b></button>
								</div>
							</div>
							<div class="d-flex justify-content-end mr-5 mt-3">
								<button class="btn btn-dark mr-3">상품 문의(쪽지)</button>
								<button class="btn btn-info mr-3">장바구니</button>
								<button class="btn btn-warning">바로 주문하기</button>
							</div>
						</c:when>
						<c:otherwise>
							<div class="d-flex justify-content-end mr-5">
								<button class="btn btn-info mr-3" onClick="location.href='/item/item_update_view?id=${itemDetailView.item.id}'"">수정하기</button>
								<button class="btn btn-danger">삭제하기</button>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="item-detail-box">
			<div>
			<%-- 탭들 --%>
				<ul class="nav nav-tabs pl-5">
				  <li class="nav-item">
				    <a class="nav-link active" href="#" data-show-content="content">상품 정보</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#" data-show-content="review">리뷰</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#" data-show-content="seller">상점 정보</a>
				  </li>
				</ul>
			</div>
			<%-- 탭 선택에 따라 보여지는 정보들 --%>
			<div class="mt-5">
				<div id="itemContent" class="tab-info">
					${itemDetailView.item.content}
				</div>
				<div id="review" class="tab-info d-none">
					<div class="pl-5">
						<div class="review-box border p-3">
							<img src="/static/img/star_yellow.png" width="30px">
							<img src="/static/img/star_yellow.png" width="30px">
							<img src="/static/img/star_yellow.png" width="30px">
							<img src="/static/img/star_yellow.png" width="30px">
							<img src="/static/img/star_yellow.png" width="30px">
							<div class="review-box">
								<h3>유저 아이디</h3>
								<h5>리뷰 내용</h5>
							</div>
						</div>
					</div>
				</div>
				<div id="sellerInfo" class="tab-info d-none w-100">
					<div class="d-flex justify-content-center">
						<div class="w-50">
							<table class="table">
								<tr>
									<td><b>상호명</b></td>
									<td>${itemDetailView.seller.shopName}</td>
								</tr>
								<tr>
									<c:set value="${fn:replace(itemDetailView.seller.address, '/', ' ')}" var="addressArr"/>
									<td><b>주소</b></td>
									<td>${addressArr} </td>
								</tr>
								<tr>
									<td><b>연락처</b></td>
									<td>${itemDetailView.seller.shopPhoneNumber}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		<%-- (-) 버튼 클릭시 클릭시 --%>
		$('#minusBtn').on('click', function() {
			let buyCount = parseInt($('#buyCount').val()) - 1;
			 $('#buyCount').val(buyCount);
			 return false;
		});
		
		<%-- (+) 버튼 클릭시 클릭시 --%>
		$('#plusBtn').on('click', function() {
			let buyCount = parseInt($('#buyCount').val()) + 1;
			 $('#buyCount').val(buyCount);
			 return false;
		});
		
		<%-- 탭 메뉴 클릭시 보여주는 정보 --%>
		
		$('.nav-item').on('click', function(e) {
			e.preventDefault();
			$('.nav-item').children().removeClass('active');
			$(this).children().addClass('active');
			
			
			// 보여줄 내용
			let showContent = $(this).children().data('show-content');
			if (showContent == 'content') {
				$('.tab-info').addClass('d-none');
				$('#itemContent').removeClass('d-none');
			} else if (showContent == 'seller') {
				$('.tab-info').addClass('d-none');
				$('#sellerInfo').removeClass('d-none');
			} else if (showContent == 'review') {
				$('.tab-info').addClass('d-none');
				$('#review').removeClass('d-none');
				$('#sellerInfo').addClass('d-none');
			}
			return false;
			
		});
	});
</script>