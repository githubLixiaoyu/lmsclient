<%@page import="com.zxtech.auth.util.AuthDetailUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
<style>
body{
	font-size: 18px;
}
</style>
</head>
<body>
	<div id="welcomeDiv"></div>
</body>
<sitemesh:script>
<script src="${pageContext.request.contextPath}/js/test.js?version=${version}"></script>
<script>
//昵称
var nickName = '<%=AuthDetailUtil.getNickName() %>';
//欢迎
var welcomeManager = '<fmt:message key="lms.welcomeManager"/>';

var welcomeMsg = welcomeManager.replace("{0}", "<span style='color: red;'>"+nickName+"</span>");
$("#welcomeDiv").html(welcomeMsg);
// alert(welcomeMsg)
</script>
</sitemesh:script>
</html>