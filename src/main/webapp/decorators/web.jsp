<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><dec:title default="Trang chủ"></dec:title></title>
 <!-- Bootstrap core CSS -->
  <link href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css'></c:url>" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<c:url value='/template/web/css/shop-homepage.css'></c:url>" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<%@ include file="/common/web/header.jsp" %>

	<!-- Page Content -->
	<dec:body/>
	<!-- /.container -->

	<!-- Footer -->
	<%@ include file="/common/web/footer.jsp" %>

	<!-- Bootstrap core JavaScript -->
 	<script src="<c:url value='/template/web/jquery/jquery.min.js'></c:url>"></script>
  	<script src="<c:url value='/template/web/bootstrap/js/bootstrap.bundle.min.js'></c:url>"></script>
</body>
</html>