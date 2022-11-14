<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 배너 이미지 --%>
<div class="d-flex justify-content-center">
	<div class="w-75" >
		<div class="d-flex justify-content-center">
			<div id="banner" class="bg-success">
				<img src="${shop.seller.bannerImg}" alt="배너이미지" width="100%" height="100%"> 
			</div>
		</div>
<%-- 버튼들 --%>
		<div class="mt-3 d-flex justify-content-end w-100">
			<c:choose>
				<c:when test="${userId ne shop.seller.userId}">
					<div>
						<c:choose>
							<c:when test="${shop.isBookmarked}">
								<button class="like-btn mr-1 btn btn-outline-info" data-seller-id="${shop.seller.id}">즐겨찾기 취소</button>
							</c:when>
							<c:otherwise>
								<button class="like-btn mr-1  btn btn-info" data-seller-id="${shop.seller.id}">즐겨찾기 등록</button>
							</c:otherwise>
						</c:choose>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<button class="btn btn-secondary" onClick="location.href='/item/item_create_view'">상품 등록하기</button>
						<button class="btn btn-dark mr-1" onClick="location.href='/seller/update_view'">설정</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
<%-- 페이징 --%>
		<div class="d-flex justify-content-center mb-3">
			<%-- 상품 갯수가 3의 배수이면 나눈 몫 그대로 마지막 페이지  --%>
			<c:choose>
				<c:when test="${shop.itemCount % 3 eq 0}">
					<c:set value="${(shop.itemCount / 3)}" var="endPage"/>
				</c:when>
				<c:otherwise>
					<c:set value="${(shop.itemCount / 3) + 1}" var="endPage"/>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="1" end="${endPage}" var="page">
				<c:choose>
					<c:when test="${page eq currentPage}">
						<button class="page-btn btn btn-success mr-2" onClick="location.href='/shop/shop_view/${sellerLoginId}?page=${page}'">${page}</button>
					</c:when>
					<c:otherwise>
						<button class="page-btn btn btn-outline-success mr-2" onClick="location.href='/shop/shop_view/${sellerLoginId}?page=${page}'">${page}</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
<%-- 상품들 --%>
		<div class="w-100">
			<div class="item-box d-flex justify-content-start flex-wrap w-100">
				<c:forEach items="${shop.itemList}" var="item">
					<div class="mb-4 col-4">
						<a href="/item/item_detail_view?itemId=${item.id}">
							<img alt="상품 썸네일 이미지" src="${item.thumbnailImg}" width="100%" height="250px">
							<h3>${item.name}</h3>
							<h4><b>${item.price}원</b></h4>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function() {
		
		<%-- 즐겨찾기 버튼 클릭 --%>
		$('.like-btn').on('click', function(e) {
			e.preventDefault();
			let sellerId = $(this).data('seller-id');
			 $.ajax({
				url: "/bookmark/" + sellerId
				, success:function(data) {
					if (data.code == 300) {
						location.reload();
						return;
					} else {
						alert(data.errorMessage);
						return;
					}
				}
				, error:function(e) {
					alert("즐겨찾기 등록에 실패했습니다. 관리자에게 문의바랍니다");
					return;
				}
			});  // ajax 끝
		}); // 즐겨찾기 버튼 끝
		
	});
</script>