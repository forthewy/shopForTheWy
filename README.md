# shopForthewy_221103 - 종합쇼핑몰 웹 개발
> 22.11.03~22.12.14: 총 42일

### 사이트 설명
* 일반 유저/상점 유저/admin 3가지 타입의 유저로 나누어 설계한 간단한 종합 쇼핑몰
* 네이버 스마트 스토어와 타 종합 쇼핑몰(G마켓, 11번가 등)을 참고하여 제작

### 사이트 기획
프로젝트는 2가지 버전으로 나누어 기획되었고, **현재 1버전까지 구현 완료됨**

|버전|설명|
|:------:|:---|
|**1**|**즐겨찾기. 바로 주문, 장바구니, 문의(쪽지), 상점 신청, 상점 승인, 주문 조회 구현**| 
|2|1버전의 심화 기능 추가가 목적. <br>1) 주문의 심화 기능(대분류, 소분류 추가) <br>2) admin 권한 강화(상점 승인 취소. 승인 기간 추가, 상점 기능에 간섭 권한 추가.) <br>3) admin에 대한 문의 추가. |

----
## 사이트 정보
시연 영상
https://youtu.be/aOVlZJo1xhk (2배속으로 보시는 걸 추천합니다)

### DB 설계

![shoppingmall](https://user-images.githubusercontent.com/111281659/209073153-7ad18236-e7d0-440c-b2e5-bb94a73afb39.png)
https://dbdiagram.io/d/637c376dc9abfc6111744e34  

-  채팅방(쪽지) DB는 이전 SNS와는 다르게 상점 대 유저이고 상대편 유저도 상점이 될수 있다는 걸 고려해 테이블을 sellerId 와 userId 로 구성했다.


### BO 설계
![20221222](https://user-images.githubusercontent.com/111281659/209073840-33d974ec-4c7c-4130-8ebf-e6adae6823c5.png)

### 기능명세
* 회원가입/로그인 - 카카오API
* 상점 홈화면 - 즐겨찾기, 상점 수정, 상품 등록(섬머노트API)
* 상품 상세화면 - 장바구니, 리뷰, 문의, 바로주문하기

* 장바구니 - 장바구니 삭제, 장바구니 주문
``` JAVA
public List<BasketItemView> generateBasketItemViewListByBasketIdList(List<Integer> basketIdList
			, HttpServletResponse response) throws IOException {
		List<BasketItemView> baskteItemViewList = new ArrayList<>();
		
		// 선택한 장바구니 목록을 가져온다.
		List<Basket> basketList = getBasketListByBasketIdList(basketIdList);
		
		for (Basket basket: basketList) {
			BasketItemView basketItemView = new BasketItemView();
			Item item = itemBO.getItemByItemId(basket.getItemId());
			// 상품 불러오기 실패시 주문 불가.
			if (ObjectUtils.isEmpty(item)) {
				log.error("[장바구니 주문] 장바구니 주문시 상품 불러오지 못함 userId:{},itemId:{}", basket.getUserId(), basket.getItemId());
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('장바구니 상품을 불러오지 못했습니다'); history.go(-1);</script>");
				out.flush(); 
				break;
			}
			basketItemView.setBasket(basket);
			basketItemView.setItem(item);
			
			baskteItemViewList.add(basketItemView);
		}
		return baskteItemViewList;
	}
```
* 주문조회 / 통계 - 주문상태 변경, 통계 차트(구글API)
``` JAVA
public List<Map<String, Object>> statsChart(HttpSession session) {
		
		List<Map<String, Object>> statsMapList = new ArrayList<>();
		
		int userId = (int) session.getAttribute("userId");
		
		List<OrderLog> orderLogList = orderLogBO.getOrderLogList(userId);
		
		for (OrderLog orderLog : orderLogList) {
			Map<String, Object> statsMap = new HashMap<>();
			statsMap.put("itemName",orderLog.getItemName());
			statsMap.put("number", orderLog.getNumber());
			statsMap.put("price", orderLog.getPrice());
			statsMapList.add(statsMap);
		}
		
		return statsMapList;
	}
```
``` JAVASCRIPT
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
```
* 상점 신청/ 승인 
