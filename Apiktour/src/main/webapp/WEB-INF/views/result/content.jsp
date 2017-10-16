<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
 // 현재 스크롤바의 위치를 저장하는 변수 (px)
        var currentScrollTop = 0;
        //var lastScrollTop = 0;
             
        // 비동기식 jQuery이므로 window load 후 jQuery를 실행해야 함
        window.onload = function() {
            // 새로고침 했을 경우를 대비한 메소드 실행
            scrollController();
             
            // 스크롤을 하는 경우에만 실행됨
            $(window).on('scroll', function() {
               scrollController();
           });
       }
        
      function scrollController() {
          //var st = $(this).scrollTop();
          var headerHeight = $('.hd_default').outerHeight();
          currentScrollTop = $(this).scrollTop();
          var hd = (headerHeight-10);
          
          
          if ( currentScrollTop > headerHeight ) {
              $('#head').removeClass('hd_default').addClass('hd_scroll');
        }
        else {            $('#head').removeClass('hd_scroll').addClass('hd_default');
        }          
      }  
</script>
<style>

</style>    


<body>
<header>
<span class="back"><a href="javascript:history.back()">뒤로가기</a></span>
<div id="head" class="hd_default">    
    <div class="img_wrap"><img class="blur" src="${pageContext.request.contextPath}/web/images/img/photographer-407068_1920.jpg" alt="img"></div>  	
    <div class="title flexCenter">
    <span class="">에너지 Lv.1</span>
    <h1>여행코스제목</h1>
    </div>
</div>
	
</header>
<div class="content_wrap flexCenter">
	<div class="sumery">
	<span>여행지1</span>
	<span class="line"></span>
	<span>여행지2</span>
	<span  class="line"></span>
	<span>여행지1</span>
	</div>

	<article class="felxCenter">
        <h3>여행지명</h3>
        <p class="sumery_memo flexCenter">여행지 요약 정보가 없습니다.</p>
        <p class="atc_img mgauto flexCenter">이미지가 없습니다.</p>
        <p class="atc_api">API정보가 없습니다.</p>
        <hr>
	</article>
    
    <article class="felxCenter">
        <h3>여행지명</h3>
        <p class="sumery_memo flexCenter">여행지 요약 정보가 없습니다.</p>
        <p class="atc_img mgauto flexCenter">이미지가 없습니다.</p>
        <p class="atc_api">API정보가 없습니다.</p>
	</article>
</div>
</body>


      <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  
      <link href="${pageContext.request.contextPath}/web/css/result/content.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/css/result/module.css" rel="stylesheet">
 
