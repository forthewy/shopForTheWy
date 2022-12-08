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
			<div class="bg-danger">
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

     // Load the Visualization API and the corechart package.
     google.charts.load('current', {'packages':['corechart']});

     // Set a callback to run when the Google Visualization API is loaded.
     google.charts.setOnLoadCallback(drawChart);

     // Callback that creates and populates a data table,
     // instantiates the pie chart, passes in the data and
     // draws it.
     function drawChart() {

       // Create the data table.
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

       // Set chart options
       var options = {'title':'How Much Pizza I Ate Last Night',
                      'width':400,
                      'height':300};

       // Instantiate and draw our chart, passing in some options.
       var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
       chart.draw(data, options);
     }
</script>