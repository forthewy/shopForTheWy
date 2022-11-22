<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="item-detail-box d-flex">
			<div class="w-50 d-flex justify-content-center align-items-center">
				<img alt="썸네일이미지" src="${itemDetailView.item.thumbnailImg}" width="550px" height="400px">
			</div>
			<div class="w-50">
				<div class="ml-3 pt-5 d-flex justify-content-between">
					<h1>${itemDetailView.item.name}</h1>
					<fmt:formatNumber value="${itemDetailView.item.price}" type="number" var="price"/>
					<h1 class="text-success mr-5">${price} 원</h1>
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
								<button class="btn btn-info mr-3" type="button" id="basketBtn" data-item-id="${itemDetailView.item.id}">장바구니</button>
								<button class="btn btn-warning" type="button" id="directOrderBtn" data-item-id="${itemDetailView.item.id}">바로 주문하기</button>
							</div>
						</c:when>
						<c:otherwise>
							<div class="d-flex justify-content-end mr-5">
								<button class="btn btn-info mr-3" onClick="location.href='/item/item_update_view?id=${itemDetailView.item.id}'"">수정하기</button>
								<button class="btn btn-danger" type="button" id="deleteBtn" data-item-id="${itemDetailView.item.id}">삭제하기</button>
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
				<%--  리뷰 --%>
				<div id="review" class="tab-info d-none">
					<div class="pl-5">
						<c:forEach items="${itemDetailView.reviewViewList}" var="reviewView">
							<div class="review-box border p-3 mb-4">
								<c:forEach var="point" begin="1" end="5">
									<c:if test="${reviewView.review.point >= point}">
										<img src="/static/img/star_yellow.png" width="30px">
									</c:if>
								</c:forEach>
								
								<div class="review-box">
									<h3>${reviewView.userLoginId}</h3>
									<h5>${reviewView.review.content}</h5>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<%-- 상점 정보 --%>
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
		
		<%-- 삭제 버튼 클릭시 --%>
		$('#deleteBtn').on('click', function() {
			
			let itemId = $(this).data('item-id');
			let sellerLoginId = "${userLoginId}";
			
			$.ajax({
				 type:"DELETE"
				 ,data:{"itemId":itemId}
			 	 ,url: "/item/delete"
			 	 ,success:function(data){
			 		 if(data.code == 300) {
			 			 alert(data.result);
			 			 location.href = "/shop/shop_view/" + sellerLoginId;
			 		 } else {
			 			 alert(data.errorMessage);
			 		 }
			 	 }
			 	 , error:function(e) {
			 		 alert("상품 삭제에 실패했습니다. 관리자에게 문의 주세요");
			 	 }
			 })
		}); // 삭제 끝
		
		<%-- 장바구니 넣기 --%>
		$('#basketBtn').on('click', function() {
			// 로그인 되어있지 않다면 로그인 화면으로 이동
			if (${userId eq null}) {
				alert('로그인 후 이용가능 합니다.');
				location.href = "/user/sign_in_view";
				return;
			}
			
			let itemId = $(this).data('item-id');
			let number = $('#buyCount').val();
			
			$.ajax({
				 type:"POST"
				 , data:{"itemId":itemId, "number":number}
				 , url:"/basket/create"
				 , success:function(data) {
					 if (data.code == 300) {
						 location.href = "/basket/basket_list_view";
					 } else {
						 alert(data.errorMessage);
					 }
				 }
				 , error: function(e) {
					 alert('장바구니 넣기에 실패했습니다. 관리자에게 문의하여 주세요');
				 }
			}); // ajax 끝
		}); // 장바구니 넣기 끝
		
		<%-- 바로 주문하기 버튼 --%>
		$('#directOrderBtn').on('click', function() {
			// 로그인 되어있지 않다면 로그인 화면으로 이동
			if (${userId eq null}) {
				alert('로그인 후 이용가능 합니다.');
				location.href = "/user/sign_in_view";
				return;
			}
			
			// 바로 주문 장바구니에 넣고, 주문서 화면으로 이동
			let itemId = $(this).data('item-id');
			let number = $('#buyCount').val();
			
			$.ajax({
			 	data:{"itemId":itemId, "number":number}
				, url:"/direct_basket/create"
				, success:function(data) {
					if (data.code == 300) {
						 location.href = "/order/order_create_view?directBasketId=" + data.directBasketId;
					 } else {
						 alert(data.errorMessage);
					 }
				 }
				, error:function(e) {
					alert('바로주문에 실패했습니다. 관리자에게 문의하여 주세요');
				}
			}); // ajax 끝 */
		});
	});
</script>