<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="box d-flex justify-content-center">
	<div class="w-75">
		<div class="pl-5 pt-2 search-item">
			<h5 class="text-secondary">검색 결과 ${fn:length(seachViewList)}건</h5>
			<hr>
			<c:forEach items="${seachViewList}" var="searchView"> 
				<div class="d-flex">
					<div class="col-2">
						<a href="/item/item_detail_view?itemId=${searchView.item.id}">
							<img src="${searchView.item.thumbnailImg}" width="150px" height="150px">
						</a>
					</div>
					<div class="col-7 border-right">
						<a href="/item/item_detail_view?itemId=${searchView.item.id}">
							<h4>${searchView.item.name}</h4>
						</a>
						<h4 class="text-success">${searchView.item.price}원</h4>
					</div>
					<div class="d-flex justify-content-start bg-light col-2">
						<h3>${searchView.sellerShopName}</h3>
					</div>
				</div>
				<hr>
			</c:forEach>
		</div>
	</div>
</div>