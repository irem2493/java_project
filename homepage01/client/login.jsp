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
                <h1>로그인</h1>
                <p></p>
            </div>
            <div class="login-form-left-side">
                <div class="login-top-wrap">
                    <span>계정이 없으신가요?</span>
                    <button class="create-account-btn"><a href="join.do">회원가입</a></button>
                </div>
                <div class="login-input-container">
                    <div class="login-input-wrap input-id">
                        <i class="far fa-envelope"></i>
                        <input placeholder="ID" type="text">
                    </div>
                    <div class="login-input-wrap input-password">
                        <i class="fas fa-key"></i>
                        <input placeholder="Password"  type="password">
                    </div>
                    
                </div>
                <div class="login-btn-wrap">
                    <button class="login-btn">로그인</button>
                    <a href="find_id.do" >아이디를 잊어버리셨나요?</a>
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
