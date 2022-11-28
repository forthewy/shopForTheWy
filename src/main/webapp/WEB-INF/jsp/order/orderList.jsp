<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="d-flex justify-content-center">
	<div class="w-100 d-flex justify-content-center">
		<aside class="col-2 d-flex justify-content-center">
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
		<section class="order-list-box col-8 pl-5">
			<h1><b>주문 조회</b></h1>
			<c:choose>
				<c:when test="${empty basketOrderViewList}">
					<div class="d-flex justify-content-center align-items-center h-100">
						<div class="display-4">주문 내역이 없습니다.</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${basketOrderViewList}" var="basketOrderView">
						<h2><fmt:formatDate value="${basketOrderView.basketOrder.createdAt}" pattern="yyyy-MM-dd"/></h2>
						<div class="d-flex">
							<div class="d-flex w-100">
								<a href="/item/item_detail_view?itemId=${basketOrderView.item.id}">
									<img alt="주문상품 대표이미지" src="${basketOrderView.item.thumbnailImg}" width="70px" height="70px">
								</a>
								<h3>${basketOrderView.item.name}</h3>	
								<h3 class="pl-3">${basketOrderView.basketOrder.number}개</h3>	
								<h3 class="pl-3">${basketOrderView.basketOrder.price}원</h3>
								<h3 class="pl-3">${fn:replace(basketOrderView.order.address, '/', ' ')}</h3>
							</div>
							<button type="button" class="review-btn btn btn-primary" data-toggle="modal" data-target="#reviewModal" data-item-id="${basketOrderView.item.id}">리뷰 남기기</button>
							<hr>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
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
      	<div class="pb-2">
      		<label for="star1">
      			<input type="radio" value="1" name="point" id="star1" class="point-star d-none">
	      		<img alt="별점" src="/static/img/star_yellow.png" width="30px;" class="star">
      		</label>
      		<label for="star2">
      			<input type="radio" value="2" name="point" id="star2" class="point-star d-none">
	      		<img alt="별점" src="/static/img/star_yellow.png" width="30px;" class="star">
      		</label>
      		<label for="star3">
      			<input type="radio" value="3" name="point" id="star3" class="point-star d-none">
	      		<img alt="별점" src="/static/img/star_yellow.png" width="30px;" class="star">
      		</label>
      		<label for="star4">
      			<input type="radio" value="4" name="point" id="star4" class="point-star d-none">
	      		<img alt="별점" src="/static/img/star_yellow.png" width="30px;" class="star">
      		</label>
      		<label for="star5">
      			<input type="radio" value="5" name="point" id="star5" class="point-star d-none">
	      		<img alt="별점" src="/static/img/star_yellow.png" width="30px;" class="star">
      		</label>
       	</div>
        <input type="text" id="reviewContent" placeholder="리뷰를 입력하세요" class="form-control">
      </div>
      <div class="modal-footer">
        <button type="button" id="reviewAddBtn" class="btn btn-primary" data-dismiss="modal">등록</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<script>
	$(document).ready(function() {
		<%-- 리뷰 버튼 클릭시 --%>
		$('.review-btn').on('click', function(e) {
			e.preventDefault();
			let itemId = $(this).data('item-id');
			// 모달에 아이템 아이디를 넣는다
			$('#reviewModal').data('item-id', itemId);
			// 리뷰 쓸때 별점 초기화
			$('.star').attr('src', "/static/img/star_yellow.png");
		});
		
		<%-- 별점 선택 --%>
		$('.point-star').on('click', function() {
			// 선택된 별점 노랑
			$(this).parent().find('img').attr('src', "/static/img/star_yellow.png");
			// 선택된 별점이전 별점 노랑
			$(this).parent().prevAll().find('img').attr('src', "/static/img/star_yellow.png");
			// 선택된 별점이후 별점 회색
			$(this).parent().nextAll().find('img').attr('src', "/static/img/star_grey.png");
			
			// 선택한 별점 넣기
			$('#reviewModal').data('point', $(this).val());
		});
		
		
		<%-- 모달의 등록 버튼 클릭시 --%>
		$('#reviewAddBtn').on('click', function(e) {
			e.preventDefault();
			let itemId =$('#reviewModal').data('item-id');
			let content = $('#reviewContent').val();
			let point = $('#reviewModal').data('point');
			
			//console.log(itemId, content);
			$.ajax({
				type: "POST"
				,data:{"itemId":itemId, "content":content, "point":point}
				,url: "/review/create"
				,success:function(data) {
					if (data.code == 300) {
						alert(data.result);
					} else {
						alert(data.errorMessage);
					}
				}
				,error:function(e) {
					alert("리뷰 등록에 실패했습니다. 관리자에게 문의바랍니다");
				}
			});
		}); // 모달 등록 버튼 클릭 끝
	});

</script>