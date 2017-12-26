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
<meta charset="utf-8"/>
<title><fmt:message key="lms.webTitle"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${pageContext.request.contextPath}/bootstrap/assets/admin/pages/css/login.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="index.html">
<%-- 	<img src="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/img/logo-big.png" alt=""/> --%>
	</a>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form class="login-form" action="login" method="post">
		<h3 class="form-title"><fmt:message key="lms.webTitle"/></h3>
		<c:if test="${param.login_error == '1' or param.login_error == '4'}">
			<div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span><fmt:message key="lms.accountOrPasswordError"/> </span>
			</div>
	    </c:if>
	    <c:if test="${param.login_error == '2' or param.login_error == '5' or param.login_error == '8' or param.login_error == '9'}">
	      <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span><fmt:message key="lms.sessionExpired"/> </span>
			</div>
	    </c:if>
	    <c:if test="${param.login_error == '3' }">
	      <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span><fmt:message key="lms.noLogin"/> </span>
			</div>
	    </c:if>
		<div class="alert alert-danger display-hide">
			<button class="close" data-close="alert"></button>
			<span>
			Enter any username and password. </span>
		</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">UserId</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="<fmt:message key="lms.account"/>" name="login_id"/>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">Password</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="<fmt:message key="lms.password"/>" name="login_pwd"/>
		</div>
		<div class="form-actions" style="text-align: center;">
			<button type="submit" class="btn btn-success uppercase"><fmt:message key="lms.loginSignIn"/></button>
		</div>
	</form>
	<!-- END LOGIN FORM -->
</div>
<div class="copyright">
	 2017 © zxtech.
</div>
<!-- END LOGIN -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/admin/pages/scripts/login.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>

jQuery(document).ready(function() {     
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	Login.init();
	Demo.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>