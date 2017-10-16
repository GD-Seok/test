
function openCosInfo(){
	$("#cosInfo").load("/ApikAm/web/jsp/result/cos_info.jsp"); 
    $('#cosInfo').css('left','0')
    $('#wrap').css('marginLeft','25vw')
    $('#wrapBg').css('display','block')
}

function closeCosInfo(){
	$("#cosInfo").hide("/ApikAm/web/jsp/result/cos_info.jsp"); 
    $('#cosInfo').css('left','-28vw')
    $('#wrap').css('marginLeft','0')
    $('#wrapBg').css('display','none')
    

/* function moveContent(){
    	
    }
*/
    
}


/*$( function() {
    $('#rsBtn').click(function(){
    	var play=$( "#play" ).val();
    var level=$('#level').val();
    var cousnum=$('#cosnum').val();

    $('#parameter').attr('action','${pageContext.request.contextPath}/web/jsp/cos_info.jsp').submit();
    return true;//전송이 가능하게 설정			
	})
});*/
