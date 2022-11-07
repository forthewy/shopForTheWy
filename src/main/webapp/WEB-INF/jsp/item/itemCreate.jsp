<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="item-create-box  w-50">
		<h1 class="pb-3">상품 등록</h1>
		<form method="post" id="itemCreateForm" action="/item/create">
			<div class="d-flex align-items-center">
				<label for="name" class="mb-3">상품명</label>
				<input type="text" id="name" name="name" class="ml-2 form-control col-4 mb-3">
			</div>
			<div class="mb-3 d-flex align-items-center">
				<label>분류</label>
				<select name="sort" class="form-control col-4 ml-2">
					<option value="옷">옷</option>
					<option value="과일">과일</option>
					<option value="문구">문구</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="content" class="mb-3">상품 설명</label>
				<div class="item-content-box bg-white">
					<textarea id="content" name="editordata"></textarea>
				</div>
			</div>
			<div class="d-flex align-items-center">
				<label for="price" class="mb-3" >가격</label>
				<input type="text" id="price" name="price" class="form-control col-4 ml-2 mb-3">
			</div>
			<div class="d-flex align-items-center">
				<label for="number" class="mb-3">수량</label>
				<input type="text" id="number" name="number" class="form-control col-4 ml-2 mb-3">
			</div>
			<div class="d-flex align-items-center">
				<label for="deliveryPrice" class="mb-3">배송비</label>
				<input type="text" id="deliveryPrice" name="deliveryPrice" class="form-control col-4 ml-2 mb-3">
			</div>
			<div>
				<label for="thumbnailImg" class="mb-3">대표 이미지</label>
				<input type="file" name="thumbnailImg" id="thumbnailImg">
			</div>
			<div class="d-flex justify-content-center">
				<button type="submit" class="btn btn-primary col-4 mb-4">등록</button>
			</div>
		</form>
	</div>
</div>

<script>
$(document).ready(function() {
	// content 를 위한 summernote
	$('#content').summernote({
		  height: 300,// 에디터 높이
		  width : 900,
		  lang: "ko-KR",					
		  placeholder: '상품 설명을 기재해주세요'
	}); // summernote 끝
	
	// 숫자만 남기는 함수
	function onlyNumber(value) {
		return value.val().replace(/[^0-9]/g,"");
	}
	
	
	// 등록
	$('#itemCreateForm').on('submit', function(e) {
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
		
		/* 유효성 검사 */
		
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
		
	});
});
</script>