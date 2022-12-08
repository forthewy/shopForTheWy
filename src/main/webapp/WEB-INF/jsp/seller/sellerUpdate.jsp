<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-100 d-flex justify-content-center">
	<div class="gray-box w-50 d-flex justify-content-center">
		<div class="bg-light seller-update-box mt-3 mb-3">
		<h1 class="text-center">상점 설정</h1>
			<div class="d-flex align-items-center mb-3 mt-3 pl-5">
				<label for="bannerImg" class="mr-5"><h3>배너</h3></label>
				<input type="file" class="ml-4" id="bannerImg" name="bannerImg" accept=".jpg, .jpeg, .png, .webp">
			</div>
			<div class="d-flex align-items-center mb-3 mt-3 pl-5">
				<label for="shopName" class="mr-5"><h3>상점명</h3></label>
				<input type="text" class=" form-control col-3" id="shopName" name="shopName" value="${seller.shopName}" placeholder="${seller.shopName}">
			</div>
			<div class="d-flex align-items-center mb-3 mt-3 pl-5">
				<label for="shopMainImg" class="mr-5"><h3>이미지</h3></label>
				<input type="file" class="" id="shopMainImg" name="shopMainImg" accept=".jpg, .jpeg, .png, .webp">
			</div>
			<div class="d-flex align-items-center mb-3 pl-5">
				<label class="pr-5"><h3>주소</h3></label>
				<div class="address-box">
					<c:set value="${fn:split(seller.address, '/')}" var="addressArr"/>
					<input type="text" class="address ml-4 mb-1 form-control col-5" id="postcode" placeholder="${addressArr[0]}"  value="${addressArr[0]}">
					<input type="text" class="address ml-4 mb-1 form-control col-8" id="roadAddress" placeholder="${addressArr[1]}" value="${addressArr[1]}">
					<input type="text" class="ml-4 form-control col-10" id="extraAddress" placeholder="${addressArr[2]}" value="${addressArr[2]}">
				</div>
			</div>
			<div class="d-flex align-items-center mb-3 mt-3 pl-5">
				<label for="address" class="mr-5"><h3>전화번호</h3></label>
				<input type="text" class=" form-control col-3" id=shopPhoneNumber name="shopPhoneNumber" placeholder="${seller.shopPhoneNumber}" value="${seller.shopPhoneNumber}">
			</div>
			<div class="d-flex justify-content-center">
				<button class="btn btn-info" type="button" id="sellerUpdateBtn" data-seller-login-id="${userLoginId}">설정 완료</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		<%-- 파일 형식 검사 --%>
		$('#bannerImg').on('change', function() {
			let bannerImg =  $('#bannerImg').val();
			let ext = bannerImg.split('.').pop().toLowerCase();
			
			let extArr = ['jpg', 'jpeg', 'png', 'webp'];
			if (!extArr.includes(ext)) {
				alert("jpg, jpeg, png, webp 형식의 파일만 배너 이미지로 업로드 가능합니다.");
				ext.val("");
				$('#bannerImg').val("");
				return;
			}
		});
		
		$('#shopMainImg').on('change', function() {
			let bannerImg =  $('#shopMainImg').val();
			let ext = bannerImg.split('.').pop().toLowerCase();
			
			let extArr = ['jpg', 'jpeg', 'png', 'webp'];
			if (!extArr.includes(ext)) {
				alert("jpg, jpeg, png, webp 형식의 파일만 상점 대표 이미지로 업로드 가능합니다.");
				ext.val("");
				$('#shopMainImg').val("");
				return;
			}
		}); // 파일 형식 검사 끝
		
		
		// 설정 완료 버튼 클릭
		$('#sellerUpdateBtn').on('click', function() {
			let formData = new FormData();
		
			// 상점명
			let shopName = $('#shopName').val();
			formData.append('shopName', shopName);
			
			// 주소			
			let postcode = $('#postcode').val();
			let roadAddress = $('#roadAddress').val();
			let extraAddress = $('#extraAddress').val();
			let address = postcode + "/" + roadAddress  + "/" + extraAddress;
			formData.append('address', address);
			
			// 전화번호는 숫자만 입력
			let shopPhoneNumber = $('#shopPhoneNumber').val().replace(/[^0-9]/g,"");
			formData.append('shopPhoneNumber', shopPhoneNumber);
			
			// 배너이미지
			formData.append('bannerImg', $('#bannerImg')[0].files[0]);
			
			// 상점 대표 이미지
			formData.append('shopMainImg', $('#shopMainImg')[0].files[0]);
			
			let sellerLoginId = $(this).data('seller-login-id');
			
			$.ajax({
				type:"POST"
				, url: "/seller/update"
				, data: formData
				, enctype: "multipart/form-data"
				, processData: false
				, contentType: false
				, success: function(data) {
					if (data.code == 300) {
						location.href = "/shop/shop_view/" + sellerLoginId;
					} else {
						alert(data.errorMessage);
					}
				}
				, error: function(e) {
					alert("상점설정 수정에 실패했습니다. 관리자에게 문의주세요");
				}
			}); // ajax 끝
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