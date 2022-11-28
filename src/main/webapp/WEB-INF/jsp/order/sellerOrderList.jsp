<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="d-flex justify-content-center">
	<div class="w-100 d-flex justify-content-center">
		<aside class="col-1 d-flex justify-content-center">
			<div class="pt-5">
				<div>
					<button class="btn btn-info mb-3" onClick="location.href='/basket_order/order_list_view'">주문 내역</button>
				</div>
				<div>
					<button class="btn btn-info mb-3"  onClick="location.href='/user/update_view'">회원정보 수정</button>
				</div>
				<c:if test="${userType eq 2}">
					<div>
						<button class="btn btn-info mb-3" onClick="location.href='/basket_order/seller_order_list_view'">상점 주문 내역</button>
					</div>
				</c:if>
			</div>
		</aside>
		<section class="order-list-box col-8 pl-5 mt-5">
			<h1 class="mb-3"><b>상점 주문 조회</b></h1>
			<%-- 필터, 검색 --%>
			<div class="d-flex">
				<div class="d-flex w-100">
					<select name="state" class="form-control col-2">
						<option selected>상태 검색</option>
						<option>주문완료</option>
						<option>상품 준비중</option>
						<option>택배발송</option>
						<option>주문취소</option>
						<option>구매확정</option>
					</select>
					<input type="text" id="searhName" class="form-control ml-5 col-4" placeholder="이름을 입력하세요">
					<button class="btn btn-dark ml-5" id="searchBtn" type="button">검색</button>
				</div>
			</div>
			<c:choose>
				<c:when test="${empty basketOrderViewList}">
					<div class="d-flex justify-content-center align-items-center h-100">
						<div class="display-4">조회된 주문 내역이 없습니다.</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${basketOrderViewList}" var="basketOrderView">
					<%-- 주문 목록 --%>
						<div class="seller-order-each-box border mt-3 mb-3 d-flex">
							<div class="dropdown d-flex align-items-center col-2">
								<c:choose>
									<c:when test="${basketOrderView.basketOrder.state eq '주문완료'}">
									  <button class="btn btn-dark dropdown-toggle w-75" type="button" id="dropdownMenuButton" data-toggle="dropdown">
									    주문완료
									  </button>
								  	</c:when>
								  	<c:when test="${basketOrderView.basketOrder.state eq '상품 준비중'}">
									  	<button class="btn btn-info dropdown-toggle w-75" type="button" id="dropdownMenuButton" data-toggle="dropdown">
										   상품 준비중
										 </button>
								  	</c:when>
									<c:when test="${basketOrderView.basketOrder.state eq '택배발송'}">
									  	<button class="btn btn-primary dropdown-toggle w-75" type="button" id="dropdownMenuButton" data-toggle="dropdown">
										   택배 발송
										 </button>
								  	</c:when>
								  	<c:when test="${basketOrderView.basketOrder.state eq '주문취소'}">
								  		<button class="btn btn-danger dropdown-toggle w-75" type="button" id="dropdownMenuButton" data-toggle="dropdown">
										   주문취소
										 </button>
								  	</c:when>
								  	<c:otherwise>
								  		<button class="btn btn-success dropdown-toggle w-75" type="button" id="dropdownMenuButton" data-toggle="dropdown">
										   구매확정
										 </button>
								  	</c:otherwise>
								</c:choose>
							  <div class="dropdown-menu" data-basket-order-id="${basketOrderView.basketOrder.id}">
							    <a class="change-state dropdown-item" href="#" data-state='주문완료'>주문완료</a>
							    <a class="change-state dropdown-item" href="#" data-state='상품 준비중'>상품 준비중</a>
							    <a class="change-state dropdown-item" href="#" data-state='택배발송'>택배발송</a>
							    <a class="change-state dropdown-item" href="#" data-state='주문취소'>주문취소</a>
							    <a class="change-state dropdown-item" href="#" data-state='구매확정'>구매확정</a>
							  </div>
							</div>
							<div class="col-10 pl-5">
								<h5>${basketOrderView.order.name}</h5>
								<h5>${fn:replace(basketOrderView.order.address, '/', ' ')}</h5>
								<h5 class="text-success">${basketOrderView.item.name}(${basketOrderView.basketOrder.number}개)</h5>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</section>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		<%-- 이름 검색 --%>
		$('#searhName').on("keyup", function(key) {
	        if(key.keyCode == 13) {
	            $('#searchBtn').click();
	        }
	    });
		
		
		<%-- 검색 버튼 클릭 --%>
		$('#searchBtn').on('click', function() {
			let searchName = $('#searhName').val();
			
			let searchState = $("select[name=state] option:selected").val();
			if (searchState == '상태 검색') {
				searchState = "";
			}	
			location.href = "/basket_order/seller_order_list_view?searchName=" + searchName + "&searchState=" + searchState;
		}); // 검색 끝
		
		<%-- 상태 변경 --%>
		$('.dropdown-item').on('click', function(e) {
			e.preventDefault();
			
			let basketOrderId = $(this).parent().data('basket-order-id');
			let state = $(this).data('state');
			
			let changeState = confirm('주문상태를 ' + state + "으로 변경하시겠습니까?");
			
			if (!changeState) {
				return;
			} else {
				alert('주문 상태를 변경합니다');
			}
			
			$.ajax({
				type:"PUT"
				, url: "/basket_order/update"
				, data:{"basketOrderId":basketOrderId, "state":state}
				, success:function(data) {
					if (data.code == 300) {
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert('주문상태 변경에 실패했습니다. 관리자에게 문의주세요');
				}
			}); // ajax 끝
		});
	});
</script>