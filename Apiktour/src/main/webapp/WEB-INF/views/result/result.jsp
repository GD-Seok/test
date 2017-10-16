<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>    
<% request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<link href="<c:url value='/resources/css/result/result.css?ver=1' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/result/slick.css?ver=1' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/result/slick-them.css?ver=1' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/result/cos_Info.css?ver=1' />" rel="stylesheet"></link>
<script type="text/javascript" src="<c:url value='/resources/js/result/slick.js?ver=1' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/result/cos_info.js?ver=1' />"></script>
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAB_ImVdT5sLLK3qg1h7obBSI8hqbvk0ZE"></script>


   
<script>
        $(document).ready(function() {         
            $('#wrapBg').click(function(evt) {
                $('#cosInfo').css('left','-28vw')
                $('#wrap').css('marginLeft','0')        
                $('#wrapBg').css('display','none')     
               evt.preventDefault();
            });
        
        });

      $('.CosInfo').click(function(){
        	var play=$( "#play" ).val();
    		var level=$('#level').val();
    		var cousnum=$('#cosnum').val();

    		$('#parameter').attr('action','${pageContext.request.contextPath}/web/jsp/result/cos_info.jsp').submit();
    		return true;//전송이 가능하게 설정		
    	})     
        
</script>
         <style type="text/css">
         .slider { /*background-color: coral;*/
            width: 100%;
            margin: 0 auto;
        }

        .slick-list{/*background-color: cadetblue;*/}

        .slick-slide { background-color: #fff;
          height: 70px; /*width: 300px !important;*/
          margin: 0px 3px;
          border: 2px solid #ccc;
          border-radius: 3px;
        }

        </style>

   <body>

   
<section id="cosInfo">
<%-- <jsp:include page="/web/jsp/cos_info.jsp" flush="false" />  --%>
    </section>

  <div id="wrap">
    <div id="wrapBg">우측 사이드바 나올 시 음영처리</div>
    <div id="container">
      <div id="filter">
        <select>
         <option>유형선택</option>
         <option>쉴래</option>
         <option>놀래</option>
        </select>
        <select>
         <option>레벨선택</option>
         <option>1Lv</option>
         <option>2Lv</option>
         <option>3Lv</option>
         <option>4Lv</option>
         <option>5Lv</option>
        </select>        
     </div>
      <div id="cosListContainer">
          <div id="cosList" class="cos slider">
        	
				<!-- <div> 검색된 정보가 없습니다.</div> -->
			

			
			   <div>
			   	<a class="flexCol CosInfo" href="javascript:void(0)" onclick="openCosInfo()">
			   		cos.getCosname() <br>			
			   	</a>
			   </div>
			   <div>
			   	<a class="flexCol CosInfo" href="javascript:void(0)" onclick="openCosInfo()">
			   		cos.getCosname() <br>			
			   	</a>
			   </div>
			   <div>
			   	<a class="flexCol CosInfo" href="javascript:void(0)" onclick="openCosInfo()">
			   		cos.getCosname() <br>			
			   	</a>
			   </div>
			   <div>
			   	<a class="flexCol CosInfo" href="javascript:void(0)" onclick="openCosInfo()">
			   		cos.getCosname() <br>			
			   	</a>
			   </div>
			    
          </div> <!-- cosList -->
      </div> <!-- cosListContainer -->
      <div id="map"></div>
    </div>
    <!-- container -->
  </div>
  <!-- wrap-->

<script>
$( function() {
$('.cosBtn').click(function(){
	
	var play=$( "#play" ).val();
var level=$('#level').val();
var cousnum=$('#cosnum').val();

$('#parameter').attr('action','/ApikAm/web/jsp/result/cos_info.jsp').submit();
return true;//전송이 가능하게 설정			
})
});
</script>



<script>   
var initloc = {lat: 33.306090, lng: 126.289434};
var mapContainer = document.getElementById('map'), 
mapOption = {zoom: 12, center: initloc, mapTypeId: 'roadmap'};
var map = new google.maps.Map(mapContainer, mapOption);

var icons = {
    offmarker: {
    icon: '${pageContext.request.contextPath}/web/images/icon/icn_maker_off.png'
    },
    onmarker: {
    icon: '${pageContext.request.contextPath}/web/images/icon/icn_maker_on.png'
    }  
};


var features = [
{ position: new google.maps.LatLng(33.306090, 126.289434),
  type: 'offmarker' }, 
{ position: new google.maps.LatLng(33.389523, 126.239259),
  type: 'offmarker' }, 
{ position: new google.maps.LatLng(33.459974, 126.831469),
  type: 'offmarker' }, 
{ position: new google.maps.LatLng(33.262067, 126.275559),
  type: 'offmarker' },
{ position: new google.maps.LatLng(33.254923, 126.408718),
  type: 'onmarker' }
    ];
// Create markers.
 features.forEach(function(feature) {
          var marker = new google.maps.Marker({
            position: feature.position,
            icon: icons[feature.type].icon,
            map: map
          });
        });
//marker.setMap(map);
google.maps.event.addDomListener(window, 'load');
    

</script>

<script type="text/javascript">
    $(document).on('ready', function() {
      $("#cosList").slick({
        infinite: true,
        centerMode: false,
        slidesToShow: 5,
        slidesToScroll: 5,
        speed: 2000
      });
    });
</script> 

