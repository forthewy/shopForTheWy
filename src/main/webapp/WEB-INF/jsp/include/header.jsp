<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex align-items-center pt-3">
	<%-- 세로 메뉴 --%>
	<div class="dropdown pl-3 pr-3">
		<a id="dropdownMenuButton" data-toggle="dropdown">
			<img src="/static/img/menu.png" alt="분류 드롭다운 버튼" width="50px">
		</a>
		<div class="dropdown-menu">
		    <a class="dropdown-item" href="#">Action</a>
		    <a class="dropdown-item" href="#">Another action</a>
		    <a class="dropdown-item" href="#">Something else here</a>
		</div>
	</div>
	<%-- 쇼핑몰 홈 화면 링크 --%>
	<div class="pl-5 pr-5">
		<a href="/home/home_view">
			<h1>WY 쇼핑몰</h1>
		</a>
	</div>
	<%-- 검색 바 --%>
	<div class="input-group col-5 pl-5">
		<input type="text" class="form-control" id="searchWord" name="searchWord">
		<div class="input-group-append">
			<button class="btn btn-dark">검색</button>
		</div>
	</div>
	<c:choose>
		<%-- 로그인시 보여지는 메뉴 --%>
		<c:when test="${not empty userId}">
			<div class="pl-5">
				<a href="#"><img class="user-menu" alt="장바구니" src="/static/img/shoppingCart.png"></a>
				<a href="#"><img class="user-menu" alt="최근 본 상품" src="/static/img/look.png" ></a>
				<a href="#"><img class="user-menu" alt="회원 정보, 상점 신청, 주문조회" src="/static/img/person.webp"></a>
				<a href="#"><img class="user-menu" alt="문의 내역" src="/static/img/letter.webp"></a>
				<a href="#"><img class="user-menu"  alt="즐겨찾기" src="/static/img/star.png"></a>
				<%-- 상점 회원 로그인시 추가로 보이는 메뉴 --%>
				<c:if test="${userType eq 2}">
					<a href="/shop/shop_view/${userLoginId}"><img class="user-menu" alt="상점 홈" src="/static/img/home.png"></a>
				</c:if>
				<%-- 로그아웃 링크 --%>
				<a href="/user/sign_out">로그아웃</a>
			</div>
		</c:when>
		<%-- 비로그인 --%>
		<c:otherwise>
			<div class="pl-5">
				<a href="/user/sign_in_view">로그인 하기</a>
			</div>
		</c:otherwise>
	</c:choose>
</div>