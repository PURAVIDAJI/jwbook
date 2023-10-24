<%@ page language = "java" contentType ="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored ="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%request.setCharacterEncoding("UTF-8");%>

<h2>Student Info update</h2>
<hr>
<form action="/student?action=update" method="post">
     <p> id : <input type="text" readonly value="${s.id}" name="id"></p>
    <p>이름 : <input type="text" readonly value="${s.name}" name="name"></p>
    <p>대학교 : <input type="text" value="${s.univ}" name="univ"></p>
    <p>생일 : <input type="text" value="${s.birth}" name="birth"></p>
    <p>이메일 : <input type="text" value="${s.email}" name="email"></p>
    <input type="submit" value="수정">
</form>

</body>
</html>