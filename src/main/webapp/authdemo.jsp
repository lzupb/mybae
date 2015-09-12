<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<header>
	<script type="text/javascript"
		src="${ctx}/baiduwke/jquery-1.6.2.min.js"></script>
</header>
<body>
	<h2>Hello World!</h2>
	<c:redirect url="${ctx}/o/oauth2/authDemo" context="/"/>
</body>
</html>
