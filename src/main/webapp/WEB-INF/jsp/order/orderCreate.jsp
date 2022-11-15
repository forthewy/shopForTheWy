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
				<img src="/static/img/menu.png" width="70px" alt="상품 썸네일 사진">
				<div class="ml-5">
					<h4>상품명</h4>
					<h4>상품 금액</h4>
					<h4>배달비</h4>
				</div>
			</div>
		</div>
		<%-- 총 금액 --%>
		<hr>
		<div class="d-flex justify-content-end">
			총 금액 = 상품 가격 + 배송비
		</div>
		<%-- 주문자 정보 --%>
		<div>
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
				<input type="text" class="ml-4 form-control col-5 mr-4" id="phoneNumber" name="phoneNumber" value="${userPhoneNumber}">
			</div>
		</div>
		<%-- 주문 버튼 --%>
		<div class="d-flex justify-content-center">
			<button class="btn btn-info">주문 완료</button>
		</div>
	</div>
</div>