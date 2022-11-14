<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="basket-list-box w-75 bg-warning">
		<h1 class="pl-3 pt-3"><b>장바구니 목록</b></h1>
		<hr>
		<%-- 장바구니 상품 --%>
		<div class="bg-success d-flex pl-3">
			<div class="col-1 bg-primary">
				<input type="checkbox">
				<img alt="장바구니 이미지" src="/static/img/letter.webp" width="80%">
			</div>
			<div class="col-9 bg-danger d-flex align-items-end justify-content-between">
				<div>
					<h2>상품 이름</h2>
					<h5>상점 이름</h5>
				</div>
				<div class="d-flex justify-content-end">
					<div>가격</div>
					<div>배송비</div>
				</div>
			</div>
			<div class="d-flex align-items-center pl-3">
				<button class="btn btn-warning">장바구니에서 삭제</button>
			</div>
		</div>
		<hr>
		<div class="bg-success d-flex pl-3">
			<div class="col-1 bg-primary">
				<input type="checkbox">
				<img alt="장바구니 이미지" src="/static/img/letter.webp" width="80%">
			</div>
			<div class="col-9 bg-danger d-flex align-items-end justify-content-between">
				<div>
					<h2>상품 이름</h2>
					<h5>상점 이름</h5>
				</div>
				<div class="d-flex justify-content-end">
					<div>가격</div>
					<div>배송비</div>
				</div>
			</div>
			<div class="d-flex align-items-center pl-3">
				<button class="btn btn-warning">장바구니에서 삭제</button>
			</div>
		</div>
		<hr>
		<div class="d-flex justify-content-end mr-3">
			<h1>총 금액 0000원</h1>
		</div>
		<div class="d-flex justify-content-center">
			<button class="btn btn-success col-2">주문하기</button>
		</div>
	</div>
</div>