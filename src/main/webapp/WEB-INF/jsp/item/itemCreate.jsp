<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="item-create-box w-75">
		<h1 class="pb-3">상품 등록</h1>
			<%-- 상품 명 --%>
			<div class="d-flex align-items-center">
				<label for="name" class="mb-3">상품명</label>
				<input type="text" id="name" name="name" class="ml-2 form-control col-4 mb-3">
			</div>
			<%-- 상품 분류 --%>
			<div class="mb-3 d-flex align-items-center">
				<label>분류</label>
				<select name="sort" class="form-control col-4 ml-2">
					<option value="옷">옷</option>
					<option value="과일">과일</option>
					<option value="문구">문구</option>
				</select>
			</div>
			<%-- 상품 설명 --%>
			<div class="mb-3">
				<label for="content" class="mb-3">상품 설명</label>
				<div class="item-content-box bg-white">
					<textarea id="content" name="editordata"></textarea>
				</div>
			</div>
			<%-- 상품 가격 --%>
			<div class="d-flex align-items-center">
				<label for="price" class="mb-3" >가격</label>
				<input type="text" id="price" name="price" class="form-control col-4 ml-2 mb-3">
			</div>
			<%-- 상품 수량 --%>
			<div class="d-flex align-items-center">
				<label for="number" class="mb-3">수량</label>
				<input type="text" id="number" name="number" class="form-control col-4 ml-2 mb-3">
			</div>
			<%-- 배송비 --%>
			<div class="d-flex align-items-center">
				<label for="deliveryPrice" class="mb-3">배송비</label>
				<input type="text" id="deliveryPrice" name="deliveryPrice" class="form-control col-4 ml-2 mb-3">
			</div>
			<%-- 대표 이미지 --%>
			<div>
				<label for="thumbnailImg" class="mb-3">대표 이미지</label>
				<input type="file" accept=".jpg, .jpeg, .png, .webp" name="thumbnailImg" id="thumbnailImg">
			</div>
			<div class="d-flex justify-content-center">
				<button type="button" id="itemCreateBtn" class="btn btn-primary col-4 mb-4" data-seller-login-id="${userLoginId}">등록</button>
			</div>
	</div>
</div>

<script>
$(document).ready(function() {
	<%-- content 를 위한 summernote --%>
	$('#content').summernote({
		height: 300,// 에디터 높이
		lang: "ko-KR",					
		placeholder: '상품 설명을 기재해주세요',
		callbacks: { // 파일 업로드(다중업로드)
			onImageUpload : function(files, editor, welEditable){
				for (var i = files.length - 1; i >= 0; i--) {
					uploadSummernoteImageFile(files[i],this);
				}
			}
		} 
	}); // summernote 끝

	// 섬머 노트 파일을 temp 에 저장한다.
	function uploadSummernoteImageFile(file, el) {
		data = new FormData();
		data.append("file", file);
		$.ajax({
			data : data,
			type : "POST",
			url : "/uploadSummernoteImageFile",
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			success : function(data) {
				$(el).summernote('editor.insertImage', data.url);
			}
		});
	}
	
	
	<%-- 파일 형식 검사 --%>
	$('#thumbnailImg').on('change', function() {
		let thumbnailImg =  $('#thumbnailImg').val();
		let ext = thumbnailImg.split('.').pop().toLowerCase();
		
		let extArr = ['jpg', 'jpeg', 'png', 'webp'];
		if (!extArr.includes(ext)) {
			alert("jpg, jpeg, png, webp 형식의 파일만 대표 이미지로 업로드 가능합니다.");
			ext.val("");
			$('#thumbnailImg').val("");
			return;
		}
	});
	
	// 숫자만 남기는 함수
	function onlyNumber(value) {
		return value.val().replace(/[^0-9]/g,"");
	}
	
	<%-- 등록 --%>
	$('#itemCreateBtn').on('click', function(e) {
		e.preventDefault();
		
		// 수량에서 숫자 외 제거 1,000 같이 쓰는 것 방지
		$('#number').val(onlyNumber($('#number')));
		let number = $('#number').val();
		
		// 가격에서 숫자 외 제거
		$('#price').val(onlyNumber($('#price')));
		let price = $('#price').val();
		
		// 배송비에서 숫자 외 제거
		$('#deliveryPrice').val(onlyNumber($('#deliveryPrice')));
		let deliveryPrice = $('#deliveryPrice').val();
		
		<%-- 유효성 검사 --%>
		
		//이름 검사
		let name =  $('#name').val();
		if (name == "") {
			alert("상품명을 등록해주세요");
			return;
		}
		
		// 가격 검사
		if (price.length < 1) {
			alert("가격을 등록해주세요");
			return;
		}
		
		// 수량 검사
		if (number == "") {
			alert("수량을 등록해주세요");
			return;
		}
		
		// 배송비는 없으면 0이므로 검사 안함
		
		// 썸네일 파일 검사
		let thumbnailImg =  $('#thumbnailImg').val();
		if (thumbnailImg == "") {
			alert('대표이미지를 등록해주세요.');
			return;
		}
		
		let formData = new FormData();
		formData.append('name', name);
		let sort = $('select[name=sort]').val();
		formData.append('sort', sort);
		let content = $('#content').val();
		formData.append('content', content);
		formData.append('price', price);
		formData.append('number', number);
		// 배송비 없으면 0원
		if (deliveryPrice != 0 && deliveryPrice != "") {
			formData.append('deliveryPrice', deliveryPrice);
		} else {
			formData.append('deliveryPrice', 0);
		}
		formData.append('thumbnailImg', $('#thumbnailImg')[0].files[0]);
		
		// 등록 후 넘어갈 주소를 위한 sellerId
		let sellerLoginId = $(this).data('seller-login-id');
		
		$.ajax({
			type:"POST"
			, data: formData
			, url: "/item/create"
			, enctype: "multipart/form-data"
			, processData: false
			, contentType: false
			, success:function(data) {
				if (data.code == 300) {
					alert(data.result);
					location.href = "/shop/shop_view/" + sellerLoginId;
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(e) {
				alert("상품 등록에 실패했습니다. 관리자에게 문의주세요");
			}
		}); // ajax 끝
	}); // 등록 끝
});
</script>