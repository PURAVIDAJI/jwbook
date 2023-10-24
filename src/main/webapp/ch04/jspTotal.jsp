<%@ page language = "java" contentType ="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP생성예제</title>
</head>
<body>
<h2>JSP 생성예제</h2>
<h3>
<h4>1. jsp  주석</h4>
<!--HTML 주석 :  화면에서는 보이지 않지만 소스 보기에는 포함 -->
<%--JSP 주석 :  화면과 소스 보기에서 보이지 않음 --%>

<h4>2. 변수,배열,함수 선언</h4>
<%!
String [] members ={ "김길동", "홍길동", "김사랑","박사랑"};
int num1 =10;

int calc(int num2) {
 return num1 +  num2;}
%>
<hr>
<h4>3. 함수의 사용</h4>
    calc(10) 메서드 실행 결과 : <%= calc(10)%>
<hr>
<h4>4. include 지시어 사용</h4>
<%@ include file ="../hello.jsp"%>
<hr>
<h4> 5. 스크립트릿 (배열 데이터 출력)</h4>
    <ul>
    <% for(String member : members){ %>
        <li><%= member%></li>

    <% } %>
    </ul>
    <hr>



</h3>
</body>
</html>