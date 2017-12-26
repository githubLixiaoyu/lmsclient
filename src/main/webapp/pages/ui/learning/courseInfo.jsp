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
.course-header {
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
    width: 1200px;
    height: 153px;
    color: #FFF;
    text-align: right;
    line-height: 153px;
    font-size: 14px;
}
.course-header-content {
 	position: absolute;
	top: 130px;
    width: 100%;
    height: 150px;
    text-align: center;
}
.course-header-content-title {
    color: #FFF;
    font-size: 40px;
}
body {
    background-color: #FFF;
}
.course-content {
	overflow:hidden;
}
.course-content-deta {
	float: left;
	width: 300px;
	height: 200px;
	margin-left: 50px;
	margin-top: 50px;
}
.course-content-deta-title {
	font-size: 15px;
	color: #373737;
	display: -moz-inline-box;
	display: inline-block;
	width: 190px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	word-break: keep-all;
	position: relative;
	top: 6px;
}
.course-content-deta-label {
	color: #FFF;
	display: -moz-inline-box;
	display: inline-block;
	width: 69px;
	background-color: #cea47c;
	text-align: center;
}
.course-content-deta-img {
	margin-top: 10px;
	width: 259px;
	height: 144px;
}
.course-content-mask {       
    position: absolute;
    top: 0px;
    filter: alpha(opacity=60);
    background-color: #777;
    z-index: 1000;
    left: 0px;
    opacity:0.5;
    -moz-opacity:0.5;
}
.course-content-mask-deta {  
    position: fixed;
    top: 5%;
    z-index: 1001;
    right: 5%;
    width: 90%;
    height: 90%;
}
.course-content-mask-close {  
    position: fixed;
    top: 5%;
    z-index: 1002;
    right: 5%;
    width: 25px;
    height: 25px;
    background: url("${pageContext.request.contextPath}/images/index/close.png") no-repeat;
    cursor: pointer;
}
.course-type-content-second-comment {
	line-height: 30px;
}
.course-type-content-second-comment-spac {
	display: -moz-inline-box;
	display: inline-block;
	width: 70px; 
}
</style>
<div class="course-header-content">
	<div id="course-header-content-title" class="course-header-content-title"></div>
</div>
<div class="course-header"></div>
<div id="course-content" class="course-content"></div>
<div id="course-content-mask" class="course-content-mask"></div>
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
<script type="text/javascript">
	var courseTypeId = <%=request.getParameter("courseTypeId")%>;
	var userId = <%=request.getParameter("userId")%>;
	var fmtVideo = '<fmt:message key="lms.video"/>';
	var fmtCourseware = '<fmt:message key="lms.courseware"/>';
	var fmtNotApplied = '<fmt:message key="lms.notApplied"/>';
	var fmtThreeDimensional = '<fmt:message key="lms.threeDimensional"/>';
</script>
<script src="${pageContext.request.contextPath}/js/ui/learning/courseInfo.js?version=${version}"></script>
<script src="${pageContext.request.contextPath}/js/common/login_username.js?version=${version}"></script>
</html>