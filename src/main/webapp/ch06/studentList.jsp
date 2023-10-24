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
<% request.setCharacterEncoding("UTF-8"); %>
<h2>Student List</h2>

<hr>
<table>
    <tr>
        <th>id</th><th>이름</th><th>대학</th><th>생일</th><th>email</th>
    </tr>
    <c:forEach var="s" items= "${students}" > //item를 꺼내서 var에 담아 리퀘스트에서 꺼내온 거니까 el로써야함
    <tr>
         <th>${s.id}</th>
         <th>${s.name}</th>
         <th>${s.univ}</th>
         <th>${s.birth}</th>
         <th>${s.email}</th>

         <th><a href = "/student?action=update&id=${s.id}">수정하기</a></th>
         <th><a href = "/student?action=delete&id=${s.id}">삭제하기</a></th>

    </tr>
    </c:forEach>


</table>
<form action ="student?action=insert" method="post">
<p>ID :      <input type="text" name="id"></p>
<p>이름 :     <input type="text" name="name"></p>
<p>대학교 :   <input type="text" name="univ"></p>
<p>생년월일 :  <input type="text" name="birth"></p>
<p>이메일 :   <input type="text" name="email"></p>
<input type="submit" value="등록">
</form>
</body>
</html>

