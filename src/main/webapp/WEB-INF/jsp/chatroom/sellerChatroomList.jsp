<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-100 d-flex justify-content-center">
	<aside class="col-2 d-flex justify-content-center">
		<div class="pt-5">
			<c:if test="${userType eq 2}">
				<div>
					<button class="btn btn-info mb-3" onClick="location.href='/chatroom/chatroom_list_view'">문의 내역</button>
				</div>
				<div>
					<button class="btn btn-info mb-3" onClick="location.href='/chatroom/seller_chatroom_list_view'">상점 문의 내역</button>
				</div>
			</c:if>
		</div>
	</aside>
	<div class="w-50 chatroom-list-box">
		<div>
			<h1 class="text-center">문의받은 내역</h1>
				<c:forEach items="${chatroomViewList}" var="chatroomView">
					<div class="d-flex justify-content-center">
						<div class="w-100 chatroom-each-box d-flex justify-content-between border ml-5">
							<div class="d-flex justify-content-start">
								<div class="text-center d-flex align-items-center">
									<div class="chatroom-state d-flex align-items-center text-white bg-success border">
										<span><b>${chatroomView.chatroom.state}</b></span>
									</div>
									<div>
										<h4 class="pl-4">${chatroomView.userName}</h4>
									</div>
								</div>
							</div>
							<button class="btn btn-info">문의 보기</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>