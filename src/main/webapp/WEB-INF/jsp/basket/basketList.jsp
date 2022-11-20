<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="basket-list-box w-75">
		<h1 class="pl-3 pt-3"><b>장바구니 목록</b></h1>
		<hr>
		<%-- 장바구니 상품 --%>
		<form action="/order/order_create_view" method="post" id="basketOrderForm">
			<c:forEach items="${basketItemList}" var="basketItem">
				<%-- 개당 가격 --%>
				<c:set value="${basketItem.item.price}" var="eachPrice"/>	
				<%-- 배송비 --%>	
				<c:set value="${basketItem.item.deliveryPrice}" var="deliveryPrice"/>
				<%-- 바구니에 넣은 갯수 --%>						
				<c:set value="${basketItem.basket.number}" var="basketNumber"/>
				<div class="d-flex pl-3">
					<div class="col-2">
						<input type="checkbox" name="check" class="col-1" value="${basketItem.basket.id}" data-price="${eachPrice * basketNumber + deliveryPrice}">
						<a href="/item/item_detail_view?itemId=${basketItem.item.id}">
							<img alt="장바구니 이미지" src="${basketItem.item.thumbnailImg}" width="80%">
						</a>
					</div>
					<div class="col-8 d-flex">
						<div class="d-flex align-items-center col-3">
							<div class="w-100">
								<h2>${basketItem.item.name}</h2>
							</div>
						</div>
						<div>
							<div class="d-flex align-items-center h-100 ">
								<h4>장바구니에 담은 갯수 : ${basketNumber}</h4>
							</div>
						</div>
						<div class="d-flex justify-content-end align-items-center ml-5">
							<div>
								<h4>개당 가격 : ${eachPrice}</h4>
								<h4>배송비 : ${deliveryPrice}</h4>
								<h4>가격 : <span class="price">${eachPrice * basketNumber + deliveryPrice}</span>원</h4>
							</div>
						</div>
					</div>
					<div class="d-flex align-items-center pl-3">
						<button class="delete-btn btn btn-danger" data-basket-id="${basketItem.basket.id}">장바구니에서 삭제</button>
					</div>
				</div>
				<hr>
			</c:forEach>
			<%-- 총 금액 --%>
			<div class="d-flex justify-content-end mr-3">
				<input type="text" value="0" id="totalPrice" class="d-none">
				<h1>총 금액 <span id="span">0</span>원</h1>
			</div>
			<div class="d-flex justify-content-center mb-5">
				<button class="btn btn-success col-2" id="orderBtn" type="submit">주문하기</button>
			</div>
		</form>
	</div>
</div>

<script>
	$(document).ready(function() {
		<%-- 체크 박스 클릭시 총금액 변하게 하기 --%>
		$("input:checkbox").on('click', function() {
			if ($(this).prop('checked') ) {
				let price = parseInt($(this).data('price'));
				let totalPrice = parseInt($('#totalPrice').val());
				$('#span').text($('#totalPrice').val(totalPrice+price).val());
		      } else {		    	  
				let price = parseInt($(this).data('price'));
				let totalPrice = parseInt($('#totalPrice').val());
				$('#span').text($('#totalPrice').val(totalPrice-price).val());
		      }
		 });
		
		<%-- 장바구니 삭제 --%>
		$('.delete-btn').on('click', function() {
			
			let basketId = $(this).data('basket-id');
			
			$.ajax({
				type:"DELETE"
				, data: {"basketId":basketId}
				, url: "/basket/delete"
				, success: function(data) {
					if (data.code == 300) {
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert('장바구니 삭제에 실패했습니다. 관리자에게 문의해주세요');
				}
			});
		}); // 장바구니 삭제 끝
		
		<%-- 주문 버튼 클릭 --%>
		$('#basketOrderForm').on('submit', function(e) {
			
			 let url = $(this).attr('action');
			 let params = $(this).serialize();
			 
			 // 아무것도 체크하지 않으면 화면이 넘어가지 않는다.
			 if (params.replace('check').length < 1) {
				 alert('주문할 상품을 선택해주세요');
				 return false;
			 }
			 
			 $.post(url, params)
			 .done({
				 // 화면으로 넘어간다
			 });
		}); // 주문 버튼 클릭 끝
	});

</script>