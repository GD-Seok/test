<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<link href="<c:url value='/resources/css/member/member.css?ver=1' />" rel="stylesheet"></link>
<script type="text/javascript" src="<c:url value='/resources/js/member/member.js?ver=1' />"></script>

<div class="container">
		<div class="header">
			<div class="logo"></div>
		</div>
		<div class="main">
			<h4>회원가입</h4>
			<div>
				<form action="#">
                    <fieldset>
                        <div>
                            <label for="email"></label> <input type="text" id="email"
                                name="firstname" placeholder="이메일 ( ex@apiktour.com)" onblur="ck_email()"> 
                            <span id="MsgId" class="none">aa</span>
                        </div>
                        <div>
                            <label for="pwd"></label> <input type="password" id="pwd"
                                name="lastname" placeholder="비밀번호" onblur="ck_pwd()"> 
                            <span id="MsgPw" class="none">유효성체크</span>
                        </div>  
                         <div>   
                            <label for="pwd_ck"></label> <input type="password" id="pwd_ck"
                            name="lastname" placeholder="비밀번호 확인" onblur="ck_pwd2()">
                             <span id="MsgPwck" class="none">유효성체크</span>
                        </div>    
                        <div>    
				            <label for="name"></label> <input type="text" id="name"
						name="lastname" placeholder="이름" onblur="ck_name()"> 
                            <span id="MsgName" class="none">유효성체크</span>
                        </div>    
                        <div id="wrap_gender"> 
                            <span id="wrap_man" class="gender">
                            <input type="radio" id="man" name="gender" onclick="ck_gender()"> <label for="man"> 남자 </label>
                            </span>
                            <span id="wrap_woman" class="gender no_line">
						    <input type="radio" id="woman" name="gender" onclick="ck_gender()"> <label for="woman" onclick="ck_gender()"> 여자 </label>
                            </span>
                         </div> 
                        <span id="MsgGender" class="none">유효성체크</span>
                        <div> 
                            <select id="country" name="country">
                                <option value="australia">20대</option>
                                <option value="canada">30대</option>
                                <option value="usa">40대</option>
                            </select> 
                        </div>

                        <input type="submit" value="Submit">
                    </fieldset>
				</form>
			</div>
		</div>
	</div>