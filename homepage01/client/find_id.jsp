<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../../include/meta_member.jsp" %>
<body>
	<%@include file="../../include/navbar_client.jsp" %>
    <div class="page-container">
        <div class="login-form-container">
            <div class="login-form-right-side">
                <div class="top-logo-wrap">
                    
                </div>
                <h1>아이디 찾기</h1>
                <p></p>
            </div>
            <div class="login-form-left-side">
                <div class="login-top-wrap">
                    <span>계정이 있으신가요?</span>
                    <button class="create-account-btn"><a href="login.do">로그인</a></button>
                </div>
                <div class="login-input-container">
                	<div class="login-input-wrap input-id">
                        <i class="fas fa-male"></i>
                        <input placeholder="Name" type="text">
                    </div>
                    <div class="login-input-wrap input-id">
                        <i class="fa fa-phone"></i>
                        <input placeholder="PhoneNumber" type="text">
                        <button class="check-btn">인증번호</button>
                    </div>
                    <div class="login-input-wrap input-id">
                        <i class="fas fa-check"></i>
                        <input placeholder="check-number" type="text">
                        <button class="check-btn">확인</button>
                    </div>
                    <!-- 인증하면 아이디 나오게 변경 -->
                    <div class="login-input-wrap input-id">
                        <i class="far fa-envelope"></i>
                        <input placeholder="ID" type="text">
                    </div>
                </div>
                <div class="login-btn-wrap">
                    <a href="find_pw.do" >비밀번호를 잊어버리셨나요?</a>
                </div>
            </div>
        </div>
    </div>
    <!-- footer -->
    <%@include file="../../include/footer_client.jsp" %>
    <!-- Js Plugins -->
   <%@include file="../../include/script_organic.jsp" %>
</body>
</html>
