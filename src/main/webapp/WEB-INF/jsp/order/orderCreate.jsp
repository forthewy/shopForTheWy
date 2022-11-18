<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center ">
	<div class="order-box w-50 bg-warning">
		<h1 class="pl-3 pt-3 text-center"><b>주문서</b></h1>
		<%-- 주문한 상품 목록 --%>
		<div class="ml-5">
			<div class="d-flex">
				<c:choose>
					<%-- 바로 주문이라면 --%>
					<c:when test="${orderView.directBasketItemView ne null}">
						<img src="${orderView.directBasketItemView.item.thumbnailImg}" width="70px" alt="상품 썸네일 사진">
						<div class="ml-5">
							<h4>${orderView.directBasketItemView.item.name}</h4>
							<h4>개당 금액: ${orderView.directBasketItemView.item.price}</h4>
							<h4>배송비 :${orderView.directBasketItemView.item.deliveryPrice}</h4>
							<h4>갯수 : ${orderView.directBasketItemView.directBasket.number}</h4>
						</div>
					</c:when>
					<%-- 장바구니 주문이라면 --%>
					<%-- <c:otherwise>
						<c:forEach items="${orderView.basketItemViewArr}" var="basketItem">
							${basketItem}
						</c:forEach>
					</c:otherwise> --%>
				</c:choose>
			</div>
		</div>
		<%-- 총 금액 --%>
		<hr>
		<div class="d-flex justify-content-end mr-5">
			<c:set value="${orderView.directBasketItemView.item.price}" var="eachPrice"/>
			<c:set value="${orderView.directBasketItemView.directBasket.number}" var="count"/>
			<c:set value="${orderView.directBasketItemView.item.deliveryPrice}" var="deliveryPrice"/>
			<h3>총 금액 = ${eachPrice * count + deliveryPrice} 원</h3>
			<c:set value="${eachPrice * count + deliveryPrice}" var="totalPrice"/>
		</div>
		<%-- 주문자 정보 --%>
		<form id="userInfoForm" action="/basket_order/create">
			<div class="d-flex align-items-center mb-3 pl-5">
				<label for="name" class="pr-5">이름</label>
				<input type="text" class="ml-4 form-control col-4" id="name" name="name" value="${userName}">
			</div>
			<div class="d-flex align-items-center mb-3 pl-5">
				<label class="pr-5">주소</label>
				<div class="address-box">
				<c:set value="${fn:split(userAddress, '/')}" var="addressArr"/>
					<input type="text" class="address ml-4 mb-1 form-control col-5" id="postcode" placeholder="${addressArr[0]}"  value="${addressArr[0]}">
					<input type="text" class="address ml-4 mb-1 form-control col-8" id="roadAddress" placeholder="${addressArr[1]}" value="${addressArr[1]}">
					<input type="text" class="ml-4 form-control col-10" id="extraAddress" placeholder="${addressArr[2]}" value="${addressArr[2]}">
				</div>
			</div>
			<div class="d-flex align-items-center mb-3 pl-5">
				<label for="phoneNumber">전화번호</label>
				<input type="text" class="ml-5 form-control col-5" id="phoneNumber" name="phoneNumber" value="${userPhoneNumber}">
			</div>
			<%-- 주문 버튼 --%>
			<div class="d-flex justify-content-center">
				<button class="btn btn-info" type="submit" id="orderFinishBtn">주문 완료</button>
			</div>
		</form>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#userInfoForm').on('submit', function(e) {
			e.preventDefault();
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			let postcode = $('#postcode').val();
			let roadAddress = $('#roadAddress').val();
			let extraAddress = $('#extraAddress').val();
			
			let address = postcode + "/" + roadAddress  + "/" + extraAddress;
			let price = $('#directPrice').val();
			// 주소 추가
			params += "&address=" + address;
			
			params += "&directBasketId=" + "${orderView.directBasketItemView.directBasket.id}";
			params += "&price=" + ${totalPrice};
			
			console.log(params);
			
			$.post(url, params)
			.done(function(data) {
				if (data.code == 300) {
					alert("주문!");
					location.href = "/home/home_view";
				} else {
					alert("실패!");
				}
			});
		});
		
	});

</script>