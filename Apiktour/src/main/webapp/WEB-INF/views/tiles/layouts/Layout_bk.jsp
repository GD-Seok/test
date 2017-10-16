<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><tiles:getAsString name="title" /></title>
<%-- <link href="<c:url value='/resources/css/layout.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/main.css?ver=1' />" rel="stylesheet"></link> --%>
<script src="<c:url value='resources/js/common/jquery-3.2.1.min.js' />"> </script>
<script src="<c:url value='resources/js/common/nav.js' />"> </script>
<tiles:insertAttribute name="css" />
</head>
  
<body>
           
	 <span id="navIcn" onclick="openNav()"></span>   
    <header id="header">
        <tiles:insertAttribute name="header" />
    </header>
	             
	           
	<div id="wrap">
		<tiles:insertAttribute name="body" />
	</div> <!-- wrap-->

</body>
</html>
