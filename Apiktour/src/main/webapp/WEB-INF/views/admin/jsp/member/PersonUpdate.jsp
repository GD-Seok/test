<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Admin.AdminDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="per" class="Admin.PersonDTO"/>
<jsp:setProperty property="*" name="per"/>
<%
 AdminDAO perMgr= new AdminDAO();
 boolean check = perMgr.PersonUpdate(per);
 System.out.println("PersonUpdate.jsp의 check : "+check);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 중복 체크</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../js/member_in.js"></script>
</head>
<body bgcolor="deepskyblue">
 <br>
 <center>
 <%
  if(check){// 회원수정 성공시
   out.println("<b>회원수정에 성공했습니다.</b><br>");
   out.println("<a href=05_admin_m_up.jsp>회원 수정 페이지로</a>");
  }else{ out.println("<b>회원수정에 실패했습니다. 다시 입력해주세요.</b><br>");
      out.println("<a href='05_admin_m_up.jsp'> 회원 수정 페이지로 </a>");}
 
 %>
 </center> 
 <a href="#" onclick="self.close()">닫기</a> <!-- window.open() <-> self.close() 창 닫기 -->
</body>
</html>