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
			<c:forEach items="${basketOrderViewList}" var="basketOrderView">
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
					<button class="btn btn-dark ml-5" type="button">검색</button>
				</div>
			</div>
			<%-- 주문 목록 --%>
				<div class="seller-order-each-box border mt-3 mb-3 d-flex">
					<div class="dropdown d-flex align-items-center col-2">
					  <button class="btn btn-secondary dropdown-toggle w-75" type="button" id="dropdownMenuButton" data-toggle="dropdown">
					    주문상태
					  </button>
					  <div class="dropdown-menu">
					    <a class="dropdown-item" href="#">변경할 상태</a>
					    <a class="dropdown-item" href="#">변경할 상태2</a>
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