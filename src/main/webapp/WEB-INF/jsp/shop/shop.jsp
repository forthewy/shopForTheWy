<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 배너 이미지 --%>
<div id="banner" class="bg-success">
	<img src="" alt="배너이미지" width="100%" height="100%"> 
</div>
<%-- 버튼들 --%>
<div class="mt-3 d-flex justify-content-between mb-5">
	<button class="ml-4 btn btn-info">이 상점을 즐겨 찾기</button>
	<div class="mr-4">
	<button class="btn btn-secondary" onClick="location.href='/item/item_create_view'">상품 등록하기</button>
	<button class="mr-3 btn btn-dark">설정</button>
	</div>
</div>
<%-- 상품들 --%>
<div class="d-flex justify-content-center">
	<div class="item-box d-flex justify-content-around">
		<div>
			<img alt="" src="/static/img/strawberry.jpg" width="300px" height="300px">
		</div>
		<div>
			<img alt="" src="/static/img/strawberry.jpg" width="300px" height="300px">
		</div>
		<div>
			<img alt="" src="/static/img/strawberry.jpg" width="300px" height="300px">
		</div>
	</div>
</div>