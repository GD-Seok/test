<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String contextPath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>링크 연결할 문자열 지정</title>
</head>
<body>
	<ul>
	<li><a href="index.do">main</a></li>
	<li><a href="menu1.do">menu1</a></li>
	<li><a href="menu2.do">menu2</a></li>
	<li><a href="<%=contextPath%>/board/list.do">목록</a></li>
	<li><a href="<%=contextPath%>/board/write.do">글쓰기</a></li>
	</ul>
</body>
</html>