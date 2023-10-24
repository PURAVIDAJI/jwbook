<%@ page language = "java" contentType ="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored = "false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h2>Product List</h2>
//리스트를 보낼 것임
<hr>
<table>

    <tr>
        <th>번호</th><th>이름</th><th>가격</th>
    </tr>
    <c:forEach var="p" items= "${products}" > //item를 꺼내서 var에 담아 리퀘스트에서 꺼내온 거니까 el로써야함
    <tr>
         <th>${p.id}</th>
         <th><a href = "/pcontrol?action=info&id=${p.id}">${p.name}</a></th>
         <th>${p.price}</th>

    </tr>
    </c:forEach>
</table>
</body>
</html>

//updatd, delete
//데이터 처리 -> dao