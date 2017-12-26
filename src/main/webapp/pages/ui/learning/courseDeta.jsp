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
.course-detail-header {
    width: 100%;
    min-height: 790px;
    background-color: #1f1f1f;
    overflow:hidden;
}
.bgstretcher-logo {
 	position: absolute;
 	left: 0px;
	top: -20px;
    width: 329px;
    height: 153px;
    background: url("${pageContext.request.contextPath}/images/index/logo.png") no-repeat left top; 
}
.bgstretcher-menu {
    position: absolute;
    top: -20px;
    right: 0px;
    width: 1200px;
    height: 153px;
    color: #FFF;
    text-align: right;
    line-height: 153px;
    font-size: 14px;
}
body {
    background-color: #FFF;
}
.course-detail-comment {
	overflow:hidden;
	width: 850px;
	margin: auto;
	left: 1%;
	right: 1%;
}
.course-detail-header-content {
 	position: absolute;
	top: 100px;
	background-color: #FFF;
	width: 980px;
	height: 560px;
	margin: auto;
	left: 1%;
	right: 1%;
}
.course-detail-header-content-img {
	width: 980px;
	height: 560px;
}
.course-detail-header-content-img-mask {
 	position: absolute;
	top: 0px;
	background-color: #000;
	width: 980px;
	height: 560px;
	margin: auto;
	filter:alpha(opacity=50);  
	-moz-opacity:0.5;  
	-khtml-opacity: 0.5;  
	opacity: 0.5;  
}
.course-detail-header-content-img-play {
 	position: absolute;
	top: 248px;
	width: 64px;
	height: 64px;
	margin: auto;
	left: 1%;
	right: 1%;
}
.course-detail-header-coursename {
 	position: relative;
	top: 680px;
	width: 850px;
	margin: auto;
	line-height: 40px;
	color: #FFF;
	font-size: 26px;
}
.course-detail-header-coursetype {
 	position: relative;
	top: 680px;
	width: 850px;
	margin: auto;
	line-height: 25px;
	color: #FFF;
}
.course-detail-header-createtime {
 	position: relative;
	top: 680px;
	width: 850px;
	margin: auto;
	line-height: 25px;
	color: #FFF;
 	margin-bottom: 700px;
}
.course-detail-comment-label {
	width: 100%;
	line-height: 25px;
	color: #000;
	line-height: 55px;
	font-size: 26px;
	margin-top: 10px;
}
.course-detail-comment-num {
	color: #AFAFAF;
	font-size: 18px;
}
.course-detail-comment-line {
	width: 100%;
	height: 2px;
	background-color: #AFAFAF;
}
.course-detail-comment-textarea {
	width: 850px;
	margin-top: 40px;
	border: solid 1px #AFAFAF;
	resize: none;
}
.course-detail-comment-button {
    background-color: #CEA47C;
    border: 0px;
    border-radius: 30px;
    color: white;
    cursor: pointer;
    display: inline-block;
    margin: 4px 2px;
    text-align: center;
    text-decoration: none;
}
.course-detail-comment-list {
	overflow: hidden;
	width: 850px;
	margin: auto;
}
.course-detail-comment-list-data {
	margin-top: 40px;
	width: 100%;
	overflow: hidden;
}
.course-detail-comment-list-line {
	margin-top: 20px;
	width: 100%;
	height: 1px;
	background-color: #AFAFAF;
}
.course-detail-comment-list-data-img {
	vertical-align: top;
	width: 70px;
	float: left;
}
.course-detail-comment-list-data-img img {
	width: 55px;
	height: 55px;
}
.course-detail-comment-list-data-content {
	width: 780px;
	overflow: hidden;
	float: left;
}
.course-detail-comment-list-data-content-ownerName {
	width: 300px;
	text-align: left;
	display: -moz-inline-box;
	display: inline-block;
}
.course-detail-comment-list-data-content-createDate {
	width: 480px;
	text-align: right;
	display: -moz-inline-box;
	display: inline-block;
}
.course-detail-comment-list-data-content-value {
	margin-top: 10px;
}
.course-detail-comment-list-data-content-reply {
	margin-top: 10px;
}
.course-detail-comment-list-data-content-click {
	width: 780px;
	overflow: hidden;
	margin-top: 10px;
}
.course-detail-comment-list-data-content-input {
	width: 712px;
	border: solid 1px #AFAFAF;
	height: 30px;
}
.course-detail-comment-list-data-sub {
	margin-top: 10px;
	width: 100%;
	overflow: hidden;
}
.course-detail-comment-list-data-sub-img {
	vertical-align: top;
	width: 60px;
	float: left;
}
.course-detail-comment-list-data-sub-img img {
	width: 45px;
	height: 45px;
}
.course-detail-comment-list-data-sub-content {
	width: 720px;
	overflow: hidden;
	float: left;
}
.course-detail-comment-list-data-sub-content-ownerName {
	width: 300px;
	text-align: left;
	display: -moz-inline-box;
	display: inline-block;
}
.course-detail-comment-list-data-sub-content-createDate {
	width: 420px;
	text-align: right;
	display: -moz-inline-box;
	display: inline-block;
}
.course-detail-comment-list-data-sub-content-value {
	margin-top: 10px;
}
.course-detail-comment-list-line-sub {
	margin-top: 10px;
	width: 100%;
	height: 1px;
	background-color: #AFAFAF;
}
</style>
<div class="course-detail-header">
	<div id="course-detail-header-content" class="course-detail-header-content"></div>
	<div id="course-detail-header-coursename" class="course-detail-header-coursename"></div>
	<div id="course-detail-header-coursetype" class="course-detail-header-coursetype"></div>
	<div id="course-detail-header-createtime" class="course-detail-header-createtime">
		<fmt:message key="lms.postedTine"/>：
	</div>
</div>
<div id="course-detail-comment" class="course-detail-comment">
	<div id="course-detail-comment-label" class="course-detail-comment-label">
		&nbsp;<fmt:message key="lms.comment"/>&nbsp;
	</div>
	<div class="course-detail-comment-line"></div>
	<textarea id="course-detail-comment-textarea" class="course-detail-comment-textarea" rows="3" cols="20" placeholder="<fmt:message key="lms.myComment"/>..." maxlength=500></textarea>
	<div align="right"><button id="course-detail-comment-button" class="course-detail-comment-button"><fmt:message key="lms.makeComments"/></button></div>
	<div id="course-detail-comment-list" class="course-detail-comment-list"></div>
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
<script type="text/javascript">
	var courseid = <%=request.getParameter("courseid")%>;
	var userId = <%=request.getParameter("userId")%>;
	var fmtMakeComments = '<fmt:message key="lms.makeComments"/>';
	var fmtReply = '<fmt:message key="lms.reply"/>';
	var fmtReplyYourOpinion = '<fmt:message key="lms.replyYourOpinion"/>';
</script>
<script src="${pageContext.request.contextPath}/js/ui/learning/courseDeta.js?version=${version}"></script>
<script src="${pageContext.request.contextPath}/js/common/login_username.js?version=${version}"></script>
</html>