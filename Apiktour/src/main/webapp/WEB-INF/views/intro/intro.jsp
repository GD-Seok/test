<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<link href="<c:url value='/resources/css/intro/intro.css' />" rel="stylesheet"></link>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.10.1/lodash.min.js"></script>
<script type="text/javascript" src="<c:url value='/resources/js/intro/ex.js' />"></script>

<div id="container">
            <div id="slide01" class="background">
                <div class="content-wrapper">
                    <h1 class="content-title"> 나는 아무 생각이 없다. </h1>
                    <p class="content-p"> 왜냐하면 로고가 있기 때문이다.</p>
                </div>            
            </div>
             
            <div id="slide02" class="background">
                <div class="content-wrapper2">
                     <section class="flexTxt"> 
                         <p> <a href="${pageContext.request.contextPath}/choice.do?mode=rest"> 쉴래? </a> </p> 
                     </section>
                     <section class="flexTxt"> 
                         <p> <a href="${pageContext.request.contextPath}/choice.do?mode=play"> 놀래? </a> </p> 
                     </section>
                </div>
            </div>
             
</div> <!-- container -->