<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="box d-flex justify-content-center">
	<div class="w-75">
		<div class="pl-5 pt-2 search-item">
			<h5 class="text-secondary">검색 결과 ${seachView.searchCount}건</h5>
			<hr>
			<c:forEach items="${seachView.itemSellerShopNameList}" var="itemSellerShopName"> 
				<div class="d-flex">
					<div class="col-2">
						<a href="/item/item_detail_view?itemId=${searchView.item.id}">
							<img src="${itemSellerShopName.item.thumbnailImg}" alt="상품 썸네일이미지" width="150px" height="150px">
						</a>
					</div>
					<div class="col-7 border-right">
						<a href="/item/item_detail_view?itemId=${itemSellerShopName.item.id}">
							<c:set var="itemName" value="${fn:replace(itemSellerShopName.item.name, searchword, searchStyle)}" />
							<h4>${itemName}</h4>
						</a>
						<h4 class="text-success">${itemSellerShopName.item.price}원</h4>
					</div>
					<div class="d-flex justify-content-start bg-light col-2">
						<h3>${itemSellerShopName.sellerShopName}</h3>
					</div>
				</div>
				<hr>
			</c:forEach>
		</div>
	</div>
</div>