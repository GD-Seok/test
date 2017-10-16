<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link href="<c:url value='/resources/css/intro/playpro.css' />" rel="stylesheet"></link>
	<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
    <script>
    $( function() {
	    $('#rsBtn').click(function(){
	    	var level=$( "#level" ).val();
			var mode=$('#mode').val();
			var count=$('#count').val();
	
			$('#parameter').attr('action','${pageContext.request.contextPath}/result.do').submit();
			return true;//전송이 가능하게 설정		
		})
    });
    </script>
    <style type="text/css">
       

    </style>
 </head>
 <body>
   
        <div id="container">           
            <p>
               ${type} 레벨값 ${ level}
            </p>
            <p class="mg01">
               <b>(${count})</b>개의 검색 결과가 있습니다.
            </p>
            <form id="parameter"  method="post">
	            <input type="hidden" name="mode" id="mode" value="${ mode}>">
	            <input type="hidden" name="level"  id="level"  value="${ level}>">
	            <input type="hidden" name="count"  id="count"  value="${count}>">
	            <button id="rsBtn"> 결과 보기 </button>
            </form>
            
            
        </div> <!-- container -->
     
 