<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center ">
	<div class="order-box w-50">
		<h1 class="pl-3 pt-3 text-center"><b>주문서</b></h1>
		<form id="orderForm" action="/basket_order/create" method="post">
		<%-- 주문한 상품 목록 --%>
			<div class="ml-5">
				<div class="d-flex">
					<c:choose>
						<%-- 바로 주문이라면 --%>
						<c:when test="${orderView.directBasketItemView ne null}">
							<img src="${orderView.directBasketItemView.item.thumbnailImg}" width="150px" height="150px" alt="상품 썸네일 사진">
							<div class="d-flex col-12">
								<%-- 바로주문 id --%>
								<c:set value="${orderView.directBasketItemView.directBasket.id}" var="directId"/>
								<%-- 개당 금액 --%>
								<c:set value="${orderView.directBasketItemView.item.price}" var="directEachPrice"/>
								<%-- 배송비 --%>
								<c:set value="${orderView.directBasketItemView.item.deliveryPrice}" var="directDeliveryPrice"/>
								<%-- 주문할 갯수 --%>
								<c:set value="${orderView.directBasketItemView.directBasket.number}" var="directCount"/>
								<div class="col-2 d-flex align-items-center border-right">
									<h5>${orderView.directBasketItemView.item.name}</h5>
								</div>
								<div class="col-2 d-flex align-items-center border-right">
									<h5>수량 : ${directCount}</h5>
								</div>
								<div class="col-3 d-flex align-items-center border-right">
									<div>
										<h5>개당 금액: ${directEachPrice}</h5>
										<h5>배송비 :${directDeliveryPrice}</h5>
									</div>
								</div>
								<%-- 주문 금액 --%>
								<c:set value="${directEachPrice * directCount + directDeliveryPrice}" var="directPrice"/>
								<div class="col-3 d-flex align-items-center">
									<h5>주문 금액 : ${directPrice}</h5>
								</div>
								<%-- 총 금액 --%>
								<c:set value="${directEachPrice * directCount + directDeliveryPrice}" var="totalPrice"/>
							</div>
						</c:when>
						<%-- 장바구니 주문이라면 --%>
						<c:otherwise>
							<div class="w-100">
								<c:forEach items="${orderView.basketItemViewList}" var="basketItem">
									<div class="d-flex w-100">
										<div>
											<img src="${basketItem.item.thumbnailImg}" width="150px" height="150px" class="mt-3" alt="상품 썸네일 사진">
										</div>
										<div class="d-flex col-12">
											<%-- 개당 금액 --%>
											<c:set value="${basketItem.item.price}" var="eachPrice"/>
											<%-- 주문할 갯수 --%>
											<c:set value="${basketItem.basket.number}" var="count"/>
											<%-- 배송비 --%>
											<c:set value="${basketItem.item.deliveryPrice}" var="deliveryPrice"/>
											<div class="col-2 d-flex align-items-center border-right">
												<h5>${basketItem.item.name}</h5>
											</div>
											<div class="col-2 d-flex align-items-center border-right">
												<h5>수량 : ${count}</h5>
											</div>
											<div class="col-3 d-flex align-items-center border-right">
												<div>
													<h5>개당 금액: ${eachPrice}</h5>
													<h5>배송비 :${deliveryPrice}</h5>
												</div>
											</div>
											<%-- 각 상품 총 금액 --%>
											<c:set value="${eachPrice * count + deliveryPrice}" var="eachTotalPrice"/>
											<div class="col-3 d-flex align-items-center">
												<h5>주문 금액 : ${eachTotalPrice}</h5>
											</div>
											<%-- 유저가 계산할 총 금액에 계속 더한다 --%>
											<c:set value="${totalPrice + eachTotalPrice}" var="totalPrice"/>
											<%-- 컨트롤러 전달용 --%>
											<input type="text" class="d-none" name="basketIdAndEachTotalPriceList" value="${basketItem.basket.id}/${eachTotalPrice}">
										</div>
									</div>
									<hr>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<%-- 총 주문 금액 --%>
			<div class="d-flex justify-content-end mr-5 pt-3">
				<h3>총 주문 금액 = ${totalPrice}<span></span>원</h3>
			</div>
			<%-- 주문자 정보 --%>
			<hr>
			<h2 class="ml-5 pb-3">주문자 정보</h2>
			<div class="d-flex align-items-center mb-3 pl-5">
				<label for="name" class="pr-5">이름</label>
				<input type="text" class="ml-4 form-control col-4" name="name" id="name"  value="${userName}">
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
				<button class="btn btn-info mb-5" type="submit" id="orderFinishBtn">주문 완료</button>
			</div>
		</form>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		// 팝업 창을 통한 입력만 가능하도록한다. (우편번호를 주소와 다르게 쓰는 것 방지)
		$('.address').on('keyup', function() {
			// 팝업 창을 띄우기 위한 클릭은 alert 없음
			if ($('#postcode').val().length < 1) {
				return false;
			}
			alert('우편번호와 주소가 다릅니다. 우편번호 재확인 바랍니다.');
			$('#postcode').val("");
			$('#roadAddress').val("");
			$('#extraAddress').val("");
			return false;
		});
		
		$('#orderForm').on('submit', function(e) {
			e.preventDefault();
			
			//공통 부분
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			let postcode = $('#postcode').val();
			let roadAddress = $('#roadAddress').val();
			let extraAddress = $('#extraAddress').val();
			
			let address = postcode + "/" + roadAddress  + "/" + extraAddress;
			
			//주소 유효성 검사
			if (postcode.length < 1) {
				alert('우편번호가 선택되지 않았습니다. 주소를 입력해주세요');
				return;
			}
			
			let name = $('#name').val().trim();
			if (name == "") {
				alert("이름이 입력되지 않았습니다.");
				return;
			}
			
			params += "&address=" + address;
			
			// 바로 주문			
			params += "&directBasketId=" + "${directId}";
			params += "&directPrice=" + "${directPrice}";
			
			//장바구니 주문
			
			console.log(params);
			
			$.post(url, params)
			.done(function(data) {
				if (data.code == 300) {
					alert("주문이 완료되었습니다. 주문 조회화면으로 이동합니다.");
					location.href = "/basket_order/order_list_view";
				} else {
					alert(data.errorMessage);
				}
			});
		});
		
	});

</script>
<%-- 카카오 지도 우편번호 API --%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$('.address').on('click', function addressPopup() {
		  	new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수

	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById("roadAddress").value = roadAddr;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if(roadAddr !== ''){
	                    document.getElementById("extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("extraAddress").value = '';
	                }
	            }
	        }).open();
	    });
</script>