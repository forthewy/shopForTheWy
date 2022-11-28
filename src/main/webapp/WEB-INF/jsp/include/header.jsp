<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex align-items-center pt-3">
	<%-- 쇼핑몰 홈 화면 링크 --%>
	<div class="pl-5 col-3">
		<a href="/home/home_view">
			<h1>WY 쇼핑몰</h1>
		</a>
	</div>
	<%-- 검색 바 --%>
	<div class="input-group col-6">
		<input type="text" class="form-control" id="searchWord" name="searchWord">
		<div class="input-group-append">
			<button class="btn btn-dark">검색</button>
		</div>
	</div>
	<c:choose>
		<%-- 로그인시 보여지는 메뉴 --%>
		<c:when test="${not empty userId}">
			<div class="pl-3 d-flex">
				<div>
					<a href="/basket/basket_list_view">
						<img class="user-menu" alt="장바구니" src="/static/img/shoppingCart.png">
					</a>
				</div>
				<%-- 최근 본 상품 --%>
				<div>
					<div>
						<a href="#" id="lastLookItemBtn">
							<img class="user-menu" alt="최근 본 상품" src="/static/img/look.png">
						</a>
					</div>
					<div class="d-none" id="lastLookItemImg">
						<a href="/item/item_detail_view?itemId=${lastLookItem.id}">
							<img src="${lastLookItem.thumbnailImg}" width="40px" height="40px">
						</a>
					</div>
				</div>
				<%-- 주문조회 화면 --%>
				<div>
					<a href="/basket_order/order_list_view" ><img class="user-menu" alt="회원 정보, 주문조회" src="/static/img/person.webp"></a>
				</div>
				<%-- 문의 내역 --%>
				<div>
					<a href="/chatroom/chatroom_list_view"><img class="user-menu" alt="문의 내역" src="/static/img/letter.webp"></a>
				</div>
				<%-- 즐겨찾기 목록 --%>
				<div>
					<a href="/bookmark/bookmark_list_view"><img class="user-menu"  alt="즐겨찾기" src="/static/img/star.png"></a>
				</div>
				<%-- 상점 회원 로그인시 추가로 보이는 메뉴 --%>
				<c:if test="${userType eq 2}">
					<div>
						<a href="/shop/shop_view/${userLoginId}"><img class="user-menu" alt="상점 홈" src="/static/img/home.png"></a>
					</div>
				</c:if>
				<%-- 로그아웃 링크 --%>
				<div class="d-flex align-items-center">
					<a href="/user/sign_out" class="pl-5">로그아웃</a>
				</div>
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

<script>
	$(document).ready(function() {
		$('#lastLookItemBtn').on('mouseover', function() {
			$('#lastLookItemBtn').addClass('d-none');
			$('#lastLookItemImg').removeClass('d-none');
		});
		
		$('#lastLookItemBtn').on('mouseout', function() {
			$('#lastLookItemImg').addClass('d-none');
			$('#lastLookItemBtn').removeClass('d-none');
		});
	})

</script>