<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-100 d-flex justify-content-center bg-success">
	<div class="w-75 bg-warning chatroom-list-box">
		<div class="">
			<h1 class="text-center">문의한 내역</h1>
			<div class="bg-danger mt-3 d-flex justify-content-center">
				<c:forEach items="${chatroomViewList}" var="chatroomView">
				<div class="d-flex border col-10 bg-light ml-5">
					<div class="chatroom-state bg-success text-white border text-center d-flex align-items-center">
						<h5><b>${chatroomView.chatroom.state}</b></h5>
					</div>
					<h4 class="col-2">상점명</h4>
					<h5 class="col-7">내용</h5>
					<button class="btn btn-info">문의 보기</button>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>