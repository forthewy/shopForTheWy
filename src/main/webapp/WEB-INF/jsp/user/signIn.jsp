<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center pt-5">
	<div class="sign-in-col d-flex align-items-center">
		<div class="sign-in-box border p-5">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">아이디</span>
		        </div>
		        <input type="text" class="form-control" id="loginId" name="loginId">
	        </div>
	        <div class="input-group">
		       	<div class="input-group-prepend">
		            <span class="input-group-text">비밀번호</span>
		        </div>
		        <input type="password" class="form-control" id="password" name="password">
		   	</div>
		   	<div class="d-flex pt-3 justify-content-center ">
				<button type="button" class="btn btn-success col-5 mr-4" id="loginBtn">로그인</button>
				<button type="button" class="btn btn-dark col-6" onclick="location.href='/user/sign_up_view'">회원가입</button>
	    	</div>
    	</div>
    </div>
</div>
<script>
	$(document).ready(function() {
		
		// 비밀번호에서 엔터키를 누르면 로그인 버튼을 누른 것과 같이 진행된다.
		$('#password').on("keyup", function(key) {
	        if(key.keyCode == 13) {
	            $('#loginBtn').click();
	        }
	    });
		
		<%-- 로그인 --%>
		$('#loginBtn').on('click', function() {
			// 아이디 검사
			let loginId = $('#loginId').val().trim();
			if (loginId.length < 1) {
				alert("아이디를 입력해주세요");
				return;
			}
			
			// 비밀번호 검사
			let password = $('#password').val();
			if (password == "") {
				alert("비밀번호를 입력하세요");
				return;
			}
			
			$.ajax({
				type:"POST"
				, url: "/user/sign_in"
				, data: {"loginId":loginId,"password":password}
				, success:function(data) {
					if (data.code == 300) {
						location.href = "/home/home_view"
					} else {
						alert(data.errorMessage);
					}
				}
				, error: function(e) {
					alert("로그인에 실패했습니다. 관리자에게 문의해주세요");
				}
			})
		});
	});
</script>