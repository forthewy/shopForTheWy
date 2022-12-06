<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-100 d-flex justify-content-center">
	<%-- 왼쪽 메뉴 --%>
	<aside class="col-2 d-flex justify-content-center">
		<div class="pt-5">
			<div>
				<button class="btn btn-info mb-3" type="button" onClick="location.href='/basket_order/order_list_view'">주문 내역</button>
			</div>
			<div>
				<button class="btn btn-info mb-3" type="button"  onClick="location.href='/user/update_view'">회원정보 수정</button>
			</div>
			<c:if test="${userType eq 2}">
				<div>
					<button class="btn btn-info mb-3" type="button" onClick="location.href='/basket_order/seller_order_list_view'">상점 주문 내역</button>
				</div>
			</c:if>
			<c:if test="${userType eq 1}">
				<div>
					<button class="btn btn-info mb-3" type="button" onClick="location.href='/seller/create_view'">상점 신청</button>
				</div>
			</c:if>
		</div>
	</aside>
	<div class="gray-box col-8 pl-5">
		<c:choose>
			<c:when test="${not empty seller}">
				<div class="d-flex justify-content-center align-items-center h-100">
					<div class="display-4">
						상점 신청을 완료하였습니다.<br>
						승인시까지 기다려주세요
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<h1 class="text-center mt-3">상점 신청</h1>
				<form action="/seller/create" method="post" id="sellerCreateForm">
					<%-- 상점 이름 --%>
					<div class="d-flex align-items-center mb-3 mt-3 pl-5">
						<label for="shopName" class="mr-5">상점명</label>
						<input type="text" class=" form-control col-3" id="shopName" name="shopName">
					</div>
					<%-- 주소 --%>
					<div class="d-flex align-items-center mb-3 pl-5">
						<label class="pr-5">주소</label>
						<div class="address-box">
							<input type="text" class="address ml-4 mb-1 form-control col-5" id="postcode" placeholder="우편번호">
							<input type="text" class="address ml-4 mb-1 form-control col-8" id="roadAddress" placeholder="도로명 주소">
							<input type="text" class="ml-4 form-control col-10" id="extraAddress" placeholder="상세 주소">
						</div>
					</div>
					<%-- 상점 전화 번호 --%>
					<div class="d-flex align-items-center mb-3 pl-5">
						<label for="shopPhoneNumber">전화 번호</label>
						<input type="text" class="ml-4 form-control col-5 mr-4" id="shopPhoneNumber" name="shopPhoneNumber">
					</div>
					<div class="d-flex justify-content-center">
						<button class="btn btn-info" type="submit">상점 신청</button>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 전화번호는 blur 일때 숫자가 아닌 값을 모두 지운다.
		$('#shopPhoneNumber').on('blur', function() {
			$(this).val($(this).val().replace(/[^0-9]/g,""));
			return false;
		});
		
		// 팝업 창을 통한 입력만 가능하도록한다. (우편번호를 주소와 다르게 쓰는 것 방지)
		$('.address').on('keyup', function() {
			// 팝업 창을 띄우기 위한 클릭은 alert 없음
			if ($('#postcode').val().length < 1) {
				return false;
			}
			alert('우편번호와 주소가 다릅니다. 우편번호 재확인 바랍니다.');
			$('#postcode').val("");
			$('#roadAddress').val("");
			$('#extraAddress').val("");
			location.reload();
			return false;
		});
		<%-- 유효성 검사 --%>
		$('#sellerCreateForm').on('submit', function(e) {
			e.preventDefault();
			
			// 상점명 검사
			let shopName = $('#shopName').val();
			if (shopName.length < 1) {
				alert("상점명을 입력해주세요");
				return;
			};
			
			// 주소 검사
			let postcode = $('#postcode').val();
			let roadAddress = $('#roadAddress').val();
			let extraAddress = $('#extraAddress').val();
			
			let address = postcode + "/" + roadAddress  + "/" + extraAddress;
			if (postcode.length < 1) {
				alert('우편번호가 선택되지 않았습니다. 주소를 입력해주세요');
				return;
			}
			
			//전화번호 검사
			let shopPhoneNumber = $('#shopPhoneNumber').val();
			if (shopPhoneNumber.length < 1) {
				alert('전화번호가 입력되지 않았습니다.');
				return;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			// address 는 나눠서 값이 나오기에 따로 추가
			params += "&address=" + address;
			
			$.post(url, params)
			.done(function(data) {
				if (data.code == 300) {
					alert("상점 신청 완료");
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			});
		});
		
	});
</script>
<%-- 카카오 지도 우편번호 API --%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$('.address').on('click', function addressPopup() {
		  	new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수

	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById("roadAddress").value = roadAddr;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if(roadAddr !== ''){
	                    document.getElementById("extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("extraAddress").value = '';
	                }
	            }
	        }).open();
	    });
</script>