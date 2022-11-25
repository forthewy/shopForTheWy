<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-100 d-flex justify-content-center">
<c:if test="${userType eq 2}">
	<aside class="col-2 d-flex justify-content-center">
		<div class="pt-5">
				<div>
					<button class="btn btn-info mb-3" onClick="location.href='/chatroom/chatroom_list_view'">문의 내역</button>
				</div>
				<div>
					<button class="btn btn-info mb-3" onClick="location.href='/chatroom/seller_chatroom_list_view'">상점 문의 내역</button>
				</div>
		</div>
	</aside>
</c:if>
	<div class="w-50 chatroom-list-box">
		<div class="">
			<h1 class="text-center">문의한 내역</h1>
			<div class="mt-3 d-flex justify-content-center">
				<c:choose>
					<c:when test="${empty chatroomViewList}">
						<div class="d-flex justify-content-center align-items-center h-100 mt-5">
							<div class="display-4">문의 내역이 없습니다.</div>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${chatroomViewList}" var="chatroomView">
							<div class="chatroom-each-box d-flex justify-content-between border col-10  ml-5">
								<div class="d-flex justify-content-start">
									<div class="text-center d-flex align-items-center">
										<div class="d-flex align-items-center text-white">
											<c:choose>
												<c:when test="${chatroomView.chatroom.state eq '답변완료'}">
													<span class="chatroom-state pt-2 border bg-success"><b>답변완료</b></span>
												</c:when>
												<c:otherwise>
													<span class="chatroom-state pt-2 border bg-info"><b>문의중</b></span>
												</c:otherwise>
											</c:choose>
										</div>
										<div>
											<h4 class="pl-4">${chatroomView.sellerShopName}</h4>
										</div>
									</div>
								</div>
								<button class="btn btn-info" onClick="location.href='/message/message_view?chatroomId=${chatroomView.chatroom.id}'">문의 보기</button>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>