<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-75 d-flex justify-content-center">
		<div>
			<div class="home-main">
				<img src="/static/img/homeMain.png" alt="홈 메인 사진" class="w-100 h-100">
			</div>
			<div class="home-menu d-flex justify-content-center align-items-center">
			 	<button type="button" class="menu-btn btn btn-danger col-2 mr-3" data-sort="옷">옷</button>
			 	<button type="button" class="menu-btn btn btn-danger col-2 mr-3" data-sort="과일">과일</button>
			 	<button type="button" class="menu-btn btn btn-danger col-2 mr-3" data-sort="문구">문구</button>
			</div>
			<div class="home-item mb-4">
				<ul class="nav nav-fill">
					<c:forEach items="${homeViewList}" var="homeView">
						<li class="home-item-card p-3 nav-item">
							<div>
								<a href="/item/item_detail_view?itemId=${homeView.item.id}">
									<img alt="상품 사진" src="${homeView.item.thumbnailImg}" width="250px" height="230px">	
								</a>
								<h3>${homeView.item.name}</h3>						
								<h5 class="text-secondary">${homeView.sellerShopName}</h5>						
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('.menu-btn').on('click', function() {
			let sort = $(this).data('sort');
			location.href = "/home/home_view?sort=" + sort;
		});
	});
</script>