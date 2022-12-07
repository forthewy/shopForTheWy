<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WY 쇼핑몰</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="/static/js/summernote/summernote-lite.js"></script>
<script src="/static/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/static/css/summernote/summernote-lite.css">


<link rel="stylesheet" type="text/css" href="/static/css/style.css">
</head>
<body>
	<div class="d-flex justify-content-center">
		<aside class="col-2 p-0 bg-grey">
			<ul class="nav">
				<li class="nav-item mt-5 pl-3 w-100">
					<a href="/admin/seller_request_view"><h5>상점 등록 요청</h5></a>
				</li>
				<li class="nav-item mt-2 pl-3 w-100">
					<a href="/home/home_view">
						<h5>쇼핑몰으로 되돌아가기</h5>
					</a>
				</li>
			</ul>
		</aside>
		<section class="col-7 admin-box bg-light">
			<div class="p-4">
				<h1 class="pb-3">상점 등록 요청</h1>
				<c:forEach items="${sellerShopNameAndIdList}" var="sellerShopNameAndId">
					<div class="bg-grey d-flex rounded seller-request-item justify-content-between align-items-center mb-3">
						<c:forEach items="${sellerShopNameAndId}" var="seller">
							<h5 class="pl-3">${seller.key}</h5>
							<div class="pr-3">
								<button class="accept-btn btn btn-info mr-3" data-seller-id="${seller.value}">승인</button>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</section>
	</div>
	<script>
		$(document).ready(function() {
			$('.accept-btn').on('click', function() {
				let sellerId = $(this).data('seller-id');
				
				
				 $.ajax({
					type: "POST"
					, data: {"sellerId":sellerId}
					, url: "/admin/seller_accept"
					, success: function(data) {
						if (data.code == 300) {
							alert("성공");
							location.reload();
						} else {
							alert("실패");
						}
					}
					, error:function(e) {
						alert("상점 승인에 실패했습니다. 관리자에게 문의주세요");
					}
				}); // ajax 끝 
			})
		});
	</script>
</body>
</html>