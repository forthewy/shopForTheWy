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
					<button class="btn btn-info mb-3">회원정보 수정</button>
				</div>
				<c:if test="${userType eq 2}">
					<div>
						<button class="btn btn-info mb-3" onClick="location.href='/basket_order/seller_order_list_view'">상점 주문 내역</button>
					</div>
				</c:if>
			</div>
		</aside>
		<section class="order-list-box col-8 pl-5 mt-5">
			<%-- 필터, 검색 --%>
			<div class="d-flex">
				<div class="d-flex w-100">
					<select name="state" class="form-control col-2">
						<option selected>
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
			<c:forEach items="${basketOrderViewList}" var="basketOrderView">
			<%-- 주문 목록 --%>
				<div class="seller-order-each-box border mt-3 mb-3 d-flex">
					<div class="dropdown d-flex align-items-center col-2">
					  <button class="btn btn-secondary dropdown-toggle w-75" type="button" id="dropdownMenuButton" data-toggle="dropdown">
					    ${basketOrderView.basketOrder.state}
					  </button>
					  <div class="dropdown-menu" data-basket-order-id="${basketOrderView.basketOrder.id}">
					    <a class="change-state dropdown-item" href="#" data-state='주문완료'>주문완료</a>
					    <a class="change-state dropdown-item" href="#" data-state='상품 준비중'>상품 준비중</a>
					    <a class="change-state dropdown-item" href="#" data-state='택배발송'>택배발송</a>
					    <a class="change-state dropdown-item" href="#" data-state='주문 취소'>주문 취소</a>
					    <a class="change-state dropdown-item" href="#" data-state='구매확정'>구매확정</a>
					  </div>
					</div>
					<div class="col-10 pl-5">
						<h5>${basketOrderView.order.name}</h5>
						<h5>${fn:replace(basketOrderView.order.address, '/', ' ')}</h5>
						<h5>${basketOrderView.item.name}(${basketOrderView.basketOrder.number}개)</h5>
					</div>
				</div>
			</c:forEach>
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
		
		
		$('#searchBtn').on('click', function() {
			let searchName = $('#searhName').val();
			location.href = "/basket_order/seller_order_list_view?searchName=" + searchName;
		}); // 이름 검색 끝
		
		<%-- 상태 변경 --%>
		$('.dropdown-item').on('click', function() {
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
						alert('성공');
						location.reload();
					} else {
						alert('실패');
					}
				}
				, error:function(e) {
					alert('주문상태 변경에 실패했습니다. 관리자에게 문의주세요');
				}
			}); // ajax 끝
		});
	});
</script>