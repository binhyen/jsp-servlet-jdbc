<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <!-- <img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon" alt="User Icon" /> -->
      <h2>Đăng nhập</h2>
    </div>

    <!-- Login Form -->
    <form action="<c:url value='/dang-nhap'/>" id="formSubmit" method="post">
     <c:if test="${not empty message}">
     	<div class="alert alert-${alert}">
    		<strong>${message}</strong>
	  	</div>
     </c:if>
	  
      <input type="text" id="login" class="fadeIn second" name="userName" placeholder="Tên đăng nhập">
      <input type="text" id="password" class="fadeIn third" name="password" placeholder="Mật khẩu">
      <input type="submit" class="fadeIn fourth" value="Đăng nhập">
      <input type="hidden" value="login" name="action"/>
    </form>

    <!-- Remind Passowrd -->
    <!-- <div id="formFooter">
      <a class="underlineHover" href="#">Forgot Password?</a>
    </div> -->

  </div>
</div>
</body>
</html>