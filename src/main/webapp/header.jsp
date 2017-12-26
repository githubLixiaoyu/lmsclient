<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
.bgstretcher-logo {
    position: fixed;
    top: 0px;
    left: 0px;
    width: 329px;
    height: 153px;
    background: url("${pageContext.request.contextPath}/images/index/logo.png") no-repeat left top; 
}
.bgstretcher-menu {
    position: fixed;
    top: 0px;
    right: 0px;
    width: 1200px;
    height: 153px;
    color: #FFF;
    text-align: right;
    line-height: 153px;
    font-size: 14px;
}
.bgstretcher-menu-a {
	color: #FFF;
	margin-right: 35px;
	text-decoration: none;
}
.bgstretcher-menu-login {
	color: #FFF;
	margin-right: 35px;
	text-decoration: none;
}
.bgstretcher-menu-logout {
	color: #FFF;
	margin-right: 35px;
	text-decoration: none;
	display: none;
}
.bgstretcher-menu-username {
	color: #FFF;
	display: none;
}
.bgstretcher-menu a:hover {
	color: #d1aa7d;
	text-decoration: none;
}
</style>
<script>
// var _hmt = _hmt || [];
// (function() {
//   var hm = document.createElement("script");
//   hm.src = "https://hm.baidu.com/hm.js?ee4c276cca1170037f863784e119a753";
//   var s = document.getElementsByTagName("script")[0]; 
//   s.parentNode.insertBefore(hm, s);
// })();
</script>
<script type="text/javascript">
	function logout(){
		location.href="${pageContext.request.contextPath}/j_spring_security_logout?flag=ui";
	}
</script>
</head>
<body>
	<div class="bgstretcher-logo"></div>
	<div class="bgstretcher-menu">
		<a target="_parent" href="${pageContext.request.contextPath}/index.jsp" class="bgstretcher-menu-a"><fmt:message key="lms.homepage"/></a>
		<a target="_parent" href="${pageContext.request.contextPath}/pages/ui/personal/personalCenter.jsp" class="bgstretcher-menu-a"><fmt:message key="lms.personalCenter"/></a>
		<a target="_parent" href="${pageContext.request.contextPath}/pages/ui/learning/learningCenter.jsp" class="bgstretcher-menu-a"><fmt:message key="lms.courseMarket"/></a>
		<a target="_parent" href="${pageContext.request.contextPath}/pages/ui/bbs/bbsInfo.jsp" class="bgstretcher-menu-a"><fmt:message key="lms.bbs"/></a>
<!-- 		<a target="_parent" href="#" class="bgstretcher-menu-a"><fmt:message key="lms.newsInformation"/></a> -->
        <a target="_parent" href="${pageContext.request.contextPath}/pages/ui/download/download.jsp" class="bgstretcher-menu-a"><fmt:message key="lms.dowloadCenter"/></a>
		<a target="_parent" href="${pageContext.request.contextPath}/pages/ui/about/about.jsp" class="bgstretcher-menu-a"><fmt:message key="lms.aboutZXtech"/></a>
		<a id="menu-login" target="_parent" href="${pageContext.request.contextPath}/login.jsp" class="bgstretcher-menu-login"><fmt:message key="lms.signIn"/></a>
		<span id="menu-username" class="bgstretcher-menu-username"></span>
		<a id="menu-logout" href="javascript:void(logout())" class="bgstretcher-menu-logout"><fmt:message key="lms.signOut"/></a>
	</div>
</body>
</html>