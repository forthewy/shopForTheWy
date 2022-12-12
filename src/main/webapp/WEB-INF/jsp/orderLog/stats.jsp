<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-100 d-flex justify-content-center">
		<aside class="col-1 d-flex justify-content-center">
			<div class="pt-5">
				<div>
					<button class="btn btn-info mb-3" onClick="location.href='/basket_order/order_list_view'">주문 내역</button>
				</div>
				<div>
					<button class="btn btn-info mb-3"  onClick="location.href='/user/update_view'">회원정보 수정</button>
				</div>
				<c:if test="${userType eq 2}">
					<div>
						<button class="btn btn-info mb-3" onClick="location.href='/basket_order/seller_order_list_view'">상점 주문 내역</button>
					</div>
					<div>
						<button class="btn btn-info mb-3" onClick="location.href='/order_log/stats_view''">상점 통계</button>
					</div>
				</c:if>
			</div>
		</aside>
		<section class="order-list-box col-8 pl-5 mt-5 d-flex">
			<div class="col-5">
				<div id="chartPie"></div>
			</div>
			<div class="col-5">
				<div id="barchart_material" style="height: 500px;"></div>
			</div>
		</section>
	</div>
</div>
 <!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	$(document).ready(function() {
		pieChart();
		barChart();
	});
	
	<%-- 파이 차트 --%> 
	function pieChart() {
		let request = 
		$.ajax({
			type : "POST",
			url : "/stats_chart"
        });   
		request.done(function(result) {
		
   	  	// 차트 시각화 api
    	 google.charts.load('current', {'packages':['corechart']});
	
	     // 차트 시각화 api 완료후 차트그리는 함수 콜백
	     google.charts.setOnLoadCallback(drawChart);

    	 function drawChart() {

	       // 차트 옵션
	       let options = {'title':'판매량 통계',
             	         'height':500,
             	         'pieHole': 0.4};
       
	       // Create the data table.
	       let data = new google.visualization.DataTable();
       
	       data.addColumn('string', '상품명');
	       data.addColumn('number', '수량');
	
	       for(let i=0; i < result.length; i++){
				data.addRow([result[i]["itemName"], parseInt(result[i]["number"])]);
			} 
         
	       // 차트 그릴 영역 지정
	       var chart = new google.visualization.PieChart(document.getElementById('chartPie'));
	       chart.draw(data, options);
    	 }
		});
	}
	<%-- 막대 그래프 --%>
	function barChart() {
		let request = 
		$.ajax({
			type : "POST",
			url : "/stats_chart"
        });
		request.done(function(result) {
		
			google.charts.load('current', {'packages':['bar']});
			google.charts.setOnLoadCallback(drawChart);

			 function drawChart() {
			     let resultArr = [];
			     resultArr.push(['상품명', '판매금액']);
			     
			     for(let i=0; i < result.length; i++) {
			    	 resultArr.push([result[i]["itemName"], parseInt(result[i]["price"])]);
				} 
			    	 
				 var data = google.visualization.arrayToDataTable(resultArr);
			      
		        var options = {
		          chart: {
		            title: '판매금액 비교',
		          },
		          bars: 'vertical',
		          colors: '#e0440e',
		          bar: {groupWidth: "40%"}
		        };

		        var chart = new google.charts.Bar(document.getElementById('barchart_material'));

		        var formatter = new google.visualization.NumberFormat({pattern: '###,###'});
		        formatter.format(data, 1);
		        chart.draw(data, options);
		      }
		}); 
	}
</script>