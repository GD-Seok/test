<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("utf-8");
	System.out.println("===cos_info.jsp===");	
	//String play=request.getParameter("play");
	//String level=request.getParameter("level");
	
	//System.out.println("level > "+level);

	//int lv=Integer.parseInt(level);
	//int cosnum=Integer.parseInt(request.getParameter("cosnum"));
	String play="play";
	int lv=1;
	int cosnum=1;
	System.out.println("play"+play+"lv:"+lv+"cosnum:"+cosnum);	


	System.out.println("============");	
	
   %>

<p>
<span class="closebtn">
<a href="javascript:void(0)" onclick="closeCosInfo()" class="txt-none"></a>
</span>
</p>
<div id="wrap_cosinfo">
    <h1 class="mg_center txt_none">코스레벨아이콘</h1>
    <div id="title_bar" class="mg_b20 mg_t20">
    <h2 class="txt_center fpx24 fw_6">%getCosname() </h2>
    <span class="like txt-none"><a href="#">
        <svg version="1.1" id="btn_like" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px"
             y="0px" width="40.603px" height="37.374px" viewBox="0 0 40.603 37.374" enable-background="new 0 0 40.603 37.374"
             xml:space="preserve">
        <path fill-rule="evenodd" clip-rule="evenodd" stroke="#fff" stroke-miterlimit="10" d="M0.516,12.696
            c-0.084-1.697,0.179-3.413,0.828-5.096C2.76,3.93,5.183,1.259,9.247,0.651c4.522-0.678,7.886,1.195,10.192,5.081
            c0.196,0.33,0.427,0.641,0.74,1.107c0.361-0.597,0.621-1.035,0.89-1.467c4.165-6.69,13.526-6.45,17.3,0.461
            c2.834,5.191,2.029,10.258-1.225,14.813c-1.906,2.669-4.511,4.866-6.934,7.134c-3.26,3.053-6.646,5.968-9.988,8.951
            c-0.367-0.27-0.634-0.432-0.861-0.637c-4.359-3.953-8.801-7.82-13.026-11.911c-1.65-1.597-3.044-3.574-4.13-5.606
            C1.201,16.699,0.615,14.711,0.516,12.696z"/>
        </svg>
        </a>
    </span>
    </div>
    <p class="txt_center mg_b20">
 <%	for(int i=0;i<cosnumList.size();i++){
	 MapDTO map = (MapDTO)cosnumList.get(i);
	 System.out.println("locname : "+map.getLocname());
	 %>
	 <span><%=map.getLocname() %></span>
<%	 
 } 
 %>
    </p>
    <p class="memo mg_b20">
        <%=cos.getIntro() %>
    </p>    
    <div id="wrap_api"> 
    <%
    for(int i=0;i<cosnumList.size();i++){
        MapDTO map=(MapDTO)cosnumList.get(i);
    %>
    <div class="list">
            <div class="api_title">
                <span class="dot"></span>
                <h3 class="fpx16 fw_6"><%=map.getLocname() %></h3>
            </div>

            <div class="api_content line_left line_h">
                <div class="list_info">           
                    <div>
                    좌표: <%=map.getLocationx() %>, <%=map.getLocationy() %><br>
                   여행지타입 : <%=map.getLoctype() %><br>
                    api 내용을 가져옵니다.<br>
                    api 내용을 가져옵니다.<br>
                    api 내용을 가져옵니다.<br>
                    </div>
                </div>
            </div>
        </div> <!-- list -->   
    <%
        }   
    %>
    </div><!-- wrap_api -->
     <a href="${pageContext.request.contextPath}/index.do?CONTROL=result&CONNAME=content"  class="mg_t20 btn_details flex_txt fpx14">상세보기</a>
    <!-- <button class="mg_t20 btn_details" onclick="moveContent()">상세보기</button> -->
</div>
