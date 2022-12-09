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
		<section class="order-list-box col-8 pl-5 mt-5">
			<div class="bg-danger chart-box">
				<div id="chart_div"></div>
			</div>
		</section>
	</div>
</div>

<script>
	$(document).ready(function() {
		
	});
	
</script>
 <!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

     // 차트 시각화 api
     google.charts.load('current', {'packages':['corechart']});

     // 차트 시각화 api 완료후 차트그리는 함수 콜백
     google.charts.setOnLoadCallback(drawChart);

     function drawChart() {

       // 차트 옵션
       var options = {'title':'판매량 통계',
                      'width': 600,
                      'height':500};
       
       // Create the data table.
       // google.visualization.arrayToDataTable(arrayList);
       var data = new google.visualization.DataTable();
       data.addColumn('string', 'Topping');
       data.addColumn('number', 'Slices');
       data.addRows([
         ['Mushrooms', 3],
         ['Onions', 1],
         ['Olives', 1],
         ['Zucchini', 1],
         ['Pepperoni', 2]
       ]);


       // 차트 그릴 영역 지정
       var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
       chart.draw(data, options);
     }
</script>