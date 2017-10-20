<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../../web/css/comm/common.css">
<link rel="stylesheet" type="text/css" href="../css/test.css">
<link rel="stylesheet" type="text/css" href="../css/admin.css">
<title><tiles:insertAttribute name="title"/></title>
</head>
<body>
	<header class="header"><tiles:insertAttribute name="header"/></header>
	<div class="wrap">
		<!-- nav UI Object -->
		<nav id="menu_v" class="menu_v">
			<!-- <h1>CSS Vertical Navigation Bar.</h1> -->
			<ul>
				<li class="active"><a href="#"><span>회원 관리 메뉴</span><span
						class="i"></span></a>
					<ul style="display: block;">
						<li><a href="#"><span>회원 목록</span></a></li>
						<li><a href="05_admin_m_up.do"><span>회원 등록</span></a></li>
						<li><a href="#"><span>회원 수정</span></a></li>
						<li><a href="#"><span>회원 삭제</span></a></li>
					</ul></li>
				<li><a href="#"><span>코스 관리 메뉴</span><span class="i"></span></a>
					<ul style="display: none;">
						<li><a href="#"><span>코스 목록</span></a>	<li>
						<li><a href="#"><span>코스 등록</span></a></li>
						<li><a href="#"><span>코스 수정</span></a></li>
						<li><a href="#"><span>코스 삭제</span></a></li>
					</ul></li>
				<li><a href="#"><span>지도 관리 메뉴</span></a>
					<ul style="display: none;">
						<li><a href="#"><span>지도 위치 목록</span></a></li>
						<li><a href="#"><span>지도 위치 등록</span></a></li>
						<li><a href="#"><span>지도 위치 수정</span></a></li>
						<li><a href="#"><span>지도 위치 삭제</span></a></li>
					</ul></li>
				<li><a href="#"><span>장소 아이콘 관리</span><span class="i"></span></a>
					<ul style="display: none;">
						<li><a href="#"><span>장소 아이콘 목록</span></a></li>
						<li><a href="#"><span>장소 아이콘 등록</span></a></li>
						<li><a href="#"><span>장소 아이콘 수정</span></a></li>
						<li><a href="#"><span>장소 아이콘 삭제</span></a></li>
					</ul></li>
			</ul>
		</nav>
		<!-- UI Object -->
		<div class="main-container">
			<!-- 본문 공간 -->
			<div><tiles:insertAttribute name="content"/></div> 
			
		</div>
		<footer ><tiles:insertAttribute name="footer"/></footer>
	</div>
</body>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/admin.js"></script>
</html>
