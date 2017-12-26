<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title><fmt:message key="lms.webTitle"/></title>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/pages/common/header.jsp"%>
</head>
<body>
<%@ include file="/header.jsp"%>
<style type="text/css">
.register-header {
    width: 100%;
    height: 230px;
    background: url("${pageContext.request.contextPath}/images/index/background.jpg");
}
.bgstretcher-logo {
 	position: absolute;
 	left: 0px;
	top: 0px;
    width: 329px;
    height: 153px;
    background: url("${pageContext.request.contextPath}/images/index/logo.png") no-repeat left top; 
}
.bgstretcher-menu {
    position: absolute;
    top: 0px;
    right: 0px;
    width: 800px;
    height: 153px;
    color: #FFF;
    text-align: right;
    line-height: 153px;
    font-size: 14px;
}
.register-header-content {
 	position: absolute;
	top: 130px;
    width: 100%;
    height: 100px;
    text-align: center;
}
.register-header-content-title {
    color: #FFF;
    font-size: 40px;
}
.register-content {
	width: 100%;
	overflow: hidden;
}
.register-input {
	width: 300px;
}
.register-content-div-logincode {
	margin: 100px auto 0px auto;
	width: 600px;
	overflow: hidden;
}
.register-content-div-name {
	margin: 20px auto 0px auto;
	width: 600px;
	overflow: hidden;
}
.register-content-div-nickname {
	margin: 20px auto 0px auto;
	width: 600px;
	overflow: hidden;
}
.register-content-div-password {
	margin: 20px auto 0px auto;
	width: 600px;
	overflow: hidden;
}
.register-content-div-standby1 {
	margin: 20px auto 0px auto;
	width: 600px;
	overflow: hidden;
}
.register-content-div-tel {
	margin: 20px auto 0px auto;
	width: 600px;
	overflow: hidden;
}
.register-content-label {
	float: left;
	width: 150px;
	text-align: right;
	line-height: 34px;
}
.register-content-input {
	float: left;
}
.register-content-div-button {
	width: 300px;
	overflow: hidden;
	margin: 50px auto 50px auto;
}
.register-content-button {
	width: 300px;
	height: 40px;
	background-color: #CBA67C;
    border: 0 none;
    border-radius: 30px;
    color: white;
    display: inline-block;
    text-align: center;
    text-decoration: none;
}
.register-content-start {
	color: red;
}
body {
    background-color: #FFF;
}
</style>
<script type="text/javascript">
function registerUser() {
	var url = server + '/common/registerUser.do';
	var data = {};
	data["logincode"] = $("#logincode").val();
	data["password"] = $("#password").val();
//	data["nickname"] = $("#nickname").val();
	data["standby1"] = $("#standby1").val();
	data["name"] = $("#name").val();
	data["tel"] = $("#tel").val();
	utils.ajax.post(url, data, function(result){
		if (result.data.flag == 1) {
			utils.dialog.alert('<fmt:message key="lms.registerSuccess"/>', function() {
				$(location).attr('href', server + '/login.jsp');
			});
		} else {
			var language = navigator.userLanguage || navigator.language;
			var registerMsg = "";
			if (language.substring(0, 2) == "zh") {
				registerMsg = result.data.msg;
			} else {
				registerMsg = result.data.msg_en;
			}
			utils.dialog.alert(registerMsg);
		}
	});
}
</script>
<div class="register-header-content">
	<div class="register-header-content-title"><fmt:message key="lms.joinFreeNow"/></div>
</div>
<div class="register-header"></div>
<div id="register-content" class="register-content">
	<div class="register-content-div-logincode">
		<div class="register-content-label"><fmt:message key="lms.account"/>&nbsp;<span class="register-content-start">*</span>&nbsp;</div>
		<div class="register-content-input"><input id="logincode" type="text" name="logincode" maxlength="50" class="form-control register-input" /></div>
	</div>
	<div class="register-content-div-password">
		<div class="register-content-label"><fmt:message key="lms.password"/>&nbsp;<span class="register-content-start">*</span>&nbsp;</div>
		<div class="register-content-input"><input id="password" type="text" name="password" maxlength="20" class="form-control register-input" /></div>
	</div>
	<%-- 2017.09.25 昵称
 	<div class="register-content-div-nickname">
		<div class="register-content-label"><fmt:message key="lms.nickName"/>&nbsp;<span class="register-content-start">*</span>&nbsp;</div>
		<div class="register-content-input"><input id="nickname" type="text" name="nickname" maxlength="50" class="form-control register-input" /></div>
	</div> 
	--%>
	<div class="register-content-div-standby1">
		<div class="register-content-label"><fmt:message key="lms.company"/>&nbsp;<span class="register-content-start">*</span>&nbsp;</div>
		<div class="register-content-input"><input id="standby1" type="text" name="standby1" maxlength="100" class="form-control register-input" /></div>
	</div>
	<div class="register-content-div-name">
		<div class="register-content-label"><fmt:message key="lms.fullName"/>&nbsp;<span class="register-content-start">*</span>&nbsp;</div>
		<div class="register-content-input"><input id="name" type="text" name="name" maxlength="50" class="form-control register-input" /></div>
	</div>
	<div class="register-content-div-tel">
		<div class="register-content-label"><fmt:message key="lms.tel"/>&nbsp;<span class="register-content-start">*</span>&nbsp;</div>
		<div class="register-content-input"><input id="tel" type="text" name="tel" maxlength="15" class="form-control register-input" /></div>
	</div>
	<div class="register-content-div-button">
		<button class="register-content-button" onclick="registerUser()"><fmt:message key="lms.register"/></button>
	</div>
</div>
<%@ include file="/pages/common/footer.jsp"%>
<%@ include file="/footer.jsp"%>
<%@ include file="/pages/common/goTop.jsp"%>
<style type="text/css">
.footer-font {
	background: #FFF;
    position: static;
	text-align: center;
	clear: both;
	width: 100%;
	color: #666666;
    font-family: "Lucida Grande",Arial,Helvetica,sans-serif;
    font-size: 12px;
}
</style>
</body>
</html>