<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<form action="list.do" name="search" method="get" onsubmit="return searchCheck()">
	<table align="center" width="200" border="0" cellspacing="0" cellpagging="2">
		<tr>
			<td align="center">
				<select name="keyField">
					<option value="locnum">id</option>
					<option value="locname">장소명</option>
				</select>
			</td>
			<td>
				<input type="text" size="16" name="keyWord">
			</td>
			<td>
				<input type="submit" value="찾기">
			</td>
		</tr>
	</table>
</form>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td align="right" colspan="5" >
			<input type = "button" value="지도 등록" onclick="location.href='05_admin_map_in.do'">
		</td>
	</tr>
	
	<tr bgcolor="#F3F3F3">
		<td width="50">id</td>
		<td width="100">장소명</td>
		<td width="70">경도 값</td>
		<td width="100">위도 값</td>
		<td width="70">장소 타입</td>
	</tr>
	<!-- 데이터의 유무에 따라서 -->
	<c:if test="${count==0}">
		<tr>
			<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach var="article" items="${list}">	
		<tr>
			<td align="center">${article.seq}</td>
			<td><a href="detail.do?seq=${article.seq }">${article.title }</a></td>
			<td>${article.writer }</td>
			<td>${article.regdate }</td>
			<td>${article.hit }</td>
		</tr>
	</c:forEach>
	
	<tr>
		<td align="center" colspan="5">${pagingHtml }</td>
	</tr>
</table>