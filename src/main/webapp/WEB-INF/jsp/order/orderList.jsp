<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center ">
	<div class="w-100 d-flex justify-content-center">
		<aside class="bg-warning col-2 d-flex justify-content-center pt-5">
			<div class="pt-3">
				<div>
					<button class="btn btn-info mb-3">주문 내역</button>
				</div>
				<div>
					<button class="btn btn-info mb-3">회원정보 수정</button>
				</div>
				<div>
					<button class="btn btn-info mb-3">상점 주문 내역</button>
				</div>
			</div>
		</aside>
		<section class="order-list-box bg-danger col-8 pl-5">
			<h1 class=""><b>주문 조회</b></h1>
			<c:forEach items="${basketOrderViewList}" var="basketOrderView">
				<h2><fmt:formatDate value="${basketOrderView.basketOrder.createdAt}" pattern="yyyy-MM-dd"/></h2>
				<div class="d-flex justify-content-">
					<div class="d-flex w-100">
						<img alt="주문상품 대표이미지" src="${basketOrderView.item.thumbnailImg}" width="70px">
						<h3>${basketOrderView.item.name}</h3>	
						<h3 class="pl-3">${basketOrderView.basketOrder.number}개</h3>	
						<h3 class="pl-3">${basketOrderView.basketOrder.price}원</h3>
					</div>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#reviewModal" data-item-id="${basketOrderView.item.thumbnailImg}">리뷰 남기기</button>
					<hr>
				</div>
			</c:forEach>
		</section>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="reviewModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">리뷰 등록</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <input type="text" id="reviewContent" placeholder="리뷰를 입력하세요" class="form-control">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">등록</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<script>
	$(document).ready(function() {
		$('.review-btn').on('click', function() {
			
		})
	})

</script>