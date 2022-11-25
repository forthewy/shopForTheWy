<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<section class="col-8 pl-5">
			<div class="gray-box">
				<h1 class="text-center">회원 정보 수정</h1>
				<form method="post" id="userUpdateForm" action="/user/update">
					<%-- 아이디 --%>
					<div class="d-flex align-items-center mb-3 mt-3 pl-5">
						<label class="pr-4 mr-2">아이디</label>
						<input type="text" class="ml-4 form-control col-3" name="loginId" value="${userLoginId}" disabled>
					</div>
					<%-- 비밀번호 --%>
					<div class="d-flex align-items-center mb-3 pl-5">
						<label for="password" class="pr-4 mr-2">비밀번호</label>
						<input type="password" class="ml-4 form-control col-4" id="password" name="password">
					</div>
					<div class="d-flex align-items-center mb-3 pl-5">
						<label for="loginId" class="pr-1">비밀번호 확인</label>
						<input type="password" class="ml-3 form-control col-4" id="passwordConfirm">
					</div>
					<%-- 이름 --%>
					<div class="d-flex align-items-center mb-3 pl-5">
						<label for="name" class="pr-5">이름</label>
						<input type="text" class="ml-4 form-control col-4" id="name" name="name" value="${userName}" placeholder="${userName}">
					</div>
					<%-- 주소 --%>
					<div class="d-flex align-items-center mb-3 pl-5">
						<label class="pr-5">주소</label>
						<c:set value="${fn:split(userAddress, '/')}" var="addressArr"/>
						<div class="address-box">
							<input type="text" class="address ml-4 mb-1 form-control col-5" id="postcode" placeholder="${addressArr[0]}"  value="${addressArr[0]}">
							<input type="text" class="address ml-4 mb-1 form-control col-8" id="roadAddress" placeholder="${addressArr[1]}" value="${addressArr[1]}">
							<input type="text" class="ml-4 form-control col-10" id="extraAddress" placeholder="${addressArr[2]}" value="${addressArr[2]}">
						</div>
					</div>
					<%-- 전화번호 --%>
					<div class="d-flex align-items-center mb-3 pl-5">
						<label for="phoneNumber">전화 번호</label>
						<input type="text" class="ml-4 form-control col-5 mr-4" id="phoneNumber" name="phoneNumber" value="${userPhoneNumber}" placeholder="${userPhoneNumber}">
						<button id="phoneNumCheckBtn" type="button" class="btn btn-info">중복 체크</button>
					</div>
					<%-- 전화번호 중복 체크 결과 --%>
					<div class="pl-5">
						<div class="text-danger d-none" id="duplicatePhoneNumberWarn">전화번호가 중복됩니다.</div>
						<div class="text-primary d-none" id="availablePhoneNumber">사용가능한 전화번호 입니다</div>
					</div>
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-dark" id="userUpdateBtn">회원정보 수정</button>
					</div>
				</form>
			</div>
		</section>
	</div>
</div>