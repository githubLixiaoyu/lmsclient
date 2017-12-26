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
.learning-header {
    width: 100%;
    height: 283px;
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
.learning-header-content {
 	position: absolute;
	top: 130px;
    width: 100%;
    height: 150px;
    text-align: center;
}
.learning-header-content-title {
    color: #FFF;
    font-size: 40px;
    line-height: 60px;
}
.learning-header-content-line {
    background-color: #CBA67C;
    width: 95px;
    height: 5px;
    margin: 10px auto;
}
.learning-header-content-note {
    color: #FFF;
    font-size: 12px;
    margin-top: 5px;
}
.learning-type {
	width: 100%;
	background-color: #FFF;
	overflow:hidden;
	background-color: #eeeeee;
	min-height:100%;height:auto;
}
.learning-type-key {
	float: left;
	width: 250px;
	height: 100%;
}
.learning-type-key-content {
	float: left;
	width: 70%;
	text-align:left;
	margin-top: 50px;
	margin-left: 30%;
	line-height: 40px;
}
.learning-type-content {
	float: left;
	width: calc(100% - 250px);
	background-color: #FFF;
	min-height: 530px;
}
.learning-type-content-tag {
	width: 100%;
	margin-left: 50px;
	margin-top: 50px;
}
.learning-type-content-tag-span {
	color: #373737;
    font-size: 16px;
}
.learning-type-content-tag-span-zh {
	color: #373737;
    font-size: 16px;
    margin-left: 40px;
    cursor: pointer;
}
.learning-type-content-tag-span-en {
	color: #373737;
    font-size: 16px;
    margin-left: 40px;
    cursor: pointer;
}
.learning-type-content-tag-span-all {
	color: #373737;
    font-size: 16px;
    margin-left: 40px;
    cursor: pointer;
}
.learning-type-content-info {
	width: 100%;
}
.learning-type-key-content-label {
	color: #373737;
	text-decoration: none;
}
.learning-type-key-content a:hover {
	color: #d1aa7d;
	text-decoration: none;
}
.course-deta-content {
	float: left;
	width: 300px;
	height: 200px;
}
.learning-type-content-first-title {
	margin-top: 40px;
	margin-left: 50px;
	font-size: 18px;
	color: #373737;
}
.learning-type-content-second {
	margin-left: 80px;
	margin-top: 30px;
}
.learning-type-content-second-title {
	font-size: 15px;
	color: #373737;
	display: -moz-inline-box;
	display: inline-block;
	width: 250px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	word-break: keep-all;
	position: relative;
	top: 6px;
}
.learning-type-content-first {
	overflow:hidden;
}
.learning-type-content-second-img {
	margin-top: 10px;
	width: 259px;
	height: 144px;
}
body {
    background-color: #FFF;
}
</style>
<div class="learning-header-content">
	<div class="learning-header-content-title"><fmt:message key="lms.courseMarket"/></div>
	<div class="learning-header-content-line"></div>
	<div class="learning-header-content-note"><fmt:message key="lms.courseMarketExplain1"/></div>
	<div class="learning-header-content-note"><fmt:message key="lms.courseMarketExplain2"/></div>
</div>
<div class="learning-header"></div>
<div class="learning-type">
	<div class="learning-type-key">
		<div id="learning-type-key" class="learning-type-key-content">
			<span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;<fmt:message key="lms.courseContent"/><br />
			<span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;<a class="learning-type-key-content-label" href="javascript:void(loadCourType(0))"><fmt:message key="lms.all"/></a><br />
			<div id="learning-type-key-info"></div>
		</div>
	</div>
	<div id="learning-type-content" class="learning-type-content">
		<div id="learning-type-content-tag" class="learning-type-content-tag">
			<span class="learning-type-content-tag-span"><fmt:message key="lms.courseLanguage"/>：</span>
			<span id="zh" showflag="0" class="learning-type-content-tag-span-zh" onclick="changeCourseLanguage(this)"><fmt:message key="lms.courseChinese"/></span>
			<span id="en" showflag="0" class="learning-type-content-tag-span-en" onclick="changeCourseLanguage(this)"><fmt:message key="lms.courseEnglish"/></span>
			<span id="all" showflag="0" class="learning-type-content-tag-span-all" onclick="changeCourseLanguage(this)"><fmt:message key="lms.courseAll"/></span>
		</div>
		<div id="learning-type-content-info" class="learning-type-content-info"></div>
	</div>
</div>
<%@ include file="/pages/common/footer.jsp"%>
<%@ include file="/footer.jsp"%>
<%@ include file="/pages/common/goTop.jsp"%>
<style type="text/css">
.footer-font {
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
<script src="${pageContext.request.contextPath}/js/ui/learning/learningCenter.js?version=${version}"></script>
<script src="${pageContext.request.contextPath}/js/common/login_username.js?version=${version}"></script>
</html>