<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-100 d-flex justify-content-center">
		<aside class="col-2 d-flex justify-content-center">
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
				<select name="state" class="form-control col-2">
					<option>주문완료</option>
					<option>상품 준비중</option>
					<option>택배발송</option>
					<option>주문취소</option>
					<option>구매확정</option>
				</select>
				<input type="text" id="searhName" class="form-control ml-5 col-2" placeholder="이름을 입력하세요">
			</div>
		</section>
	</div>
</div>