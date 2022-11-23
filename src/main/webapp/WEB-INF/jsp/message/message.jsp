<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center bg-success">
	<div class="w-75">
		<%-- 여기는 메세지 화면 --%>
		<div class="message-box bg-warning d-flex align-items-end">
			<div class="w-100">
				<div class="d-flex justify-content-start pl-5 pt-3">
					<div class="card text-bg-primary mb-3" style="max-width: 400px;">
					  <div class="card-header">다른 사람</div>
					  <div class="card-body">
					    <p class="card-text">내용</p>
					  </div>
					</div>
				</div>
				<div class="d-flex justify-content-end pr-5 pt-3">
					<div class="card text-bg-warning mb-3" style="max-width: 400px;">
					  <div class="card-header">본인</div>
					  <div class="card-body">
					    <p class="card-text">내용</p>
					  </div>
					</div>
				</div>
			</div>
		</div>
		<%-- 메세지 보내기 --%>
		<div class="input-group">
			<input type="text" class="form-control" id="searchWord" name="searchWord">
			<div class="input-group-append">
				<button class="btn btn-dark">보내기</button>
			</div>
		</div>
	</div>
</div>