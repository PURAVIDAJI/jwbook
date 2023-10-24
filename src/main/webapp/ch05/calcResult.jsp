<%@ page language = "java" contentType ="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored = "false"%>

<%
    Integer result = (Integer)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h2>Hello World!</h2>
${result}
</body>
</html>