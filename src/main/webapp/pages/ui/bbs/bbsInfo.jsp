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
.hand {
    cursor: pointer;
}
.bbs-header {
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
.bbs-header-content {
 	position: absolute;
	top: 130px;
    width: 100%;
    height: 150px;
    text-align: center;
}
.bbs-header-content-title {
    color: #FFF;
    font-size: 40px;
    line-height: 60px;
}
.bbs-header-content-line {
    background-color: #CBA67C;
    width: 95px;
    height: 5px;
    margin: 10px auto;
}
.bbs-header-content-note {
    color: #FFF;
    font-size: 12px;
    margin-top: 5px;
}
.bbs-content {
	overflow: hidden;
	width: 850px;
	margin: auto;
	left: 1%;
	right: 1%;
}
.bbs-content-data {
	overflow: hidden;
	width: 100%;
}
.bbs-content-self {
	margin-top: 10px;
	background-color: #FFF;
	width: 100%;
	height: 120px;
}
.bbs-content-self-img {
	width: 120px;
	height: 120px;
}
.bbs-content-self-add {
	display: -moz-inline-box;
	display: inline-block;
	width: 200px;
	text-align: center;
	font-size: 30px;
	cursor: pointer;
	color: #666666;
}
.bbs-content-self-nickName {
	display: -moz-inline-box;
	display: inline-block;
	width: 520px;
	text-align: right;
	vertical-align: bottom;
	color: #666666;
	font-size: 50px;
}
.bbs-content-self-mask {       
    position: absolute;
    top: 0px;
    filter: alpha(opacity=60);
    background-color: #777;
    z-index: 1000;
    left: 0px;
    opacity:0.5;
    -moz-opacity:0.5;
}
.bbs-content-add {
    position: absolute;
    top: 50%;
    left: 50%;
    z-index: 1001;
    background-color: #FFF;
    width: 800px;
    height: 700px;
    margin-top: -350px;
    margin-left: -400px; 
}
.bbs-content-add-subject {
	margin-top: 50px;
	margin-left: 50px;
}
.bbs-content-add-frame {
	border: 1px solid #666666;
	margin-top: 50px;
	margin-left: 50px;
	width: 700px;
	height: 550px;
}
.bbs-content-add-subject-textarea {
	width: 698px;
	resize: none;
	overflow-y: hidden;
	overflow-x: hidden;
	border: 0;
	padding: 5px;
}
.bbs-content-add-line {
	width: 100%;
	height: 1px;
	background-color: #666666;
}
.bbs-content-add-type {
	display: -moz-inline-box;
	display: inline-block;
	width: 40px;
	background-color: #666666;
	margin-top: 20px;
	margin-left: 20px;
	margin-bottom: 20px;
	color: #FFF;
	text-align: center;
    cursor: pointer;
}
.bbs-content-add-body-textarea {
	width: 698px;
	height: 420px;
	resize: none;
	overflow-y: hidden;
	overflow-x: hidden;
	border: 0;
	padding: 5px;
}
.bbs-content-add-type-span {
	color: #919191;
	margin-left: 5px;
}
.bbs-content-add-button {
	text-align: right;
	margin-top: 35px;
}
.bbs-content-add-button-save {
    background-color: #CEA47C;
    border: 0px;
    border-radius: 30px;
    color: white;
    cursor: pointer;
    display: inline-block;
    text-align: center;
    text-decoration: none;
    margin-right: 30px;
}
.bbs-content-add-button-close {
    background-color: #666666;
    border: 0px;
    border-radius: 30px;
    color: white;
    cursor: pointer;
    display: inline-block;
    text-align: center;
    text-decoration: none;
}
.bbs-content-info {
	width: 100%;
	overflow: hidden;
	margin-top: 25px;
}
.bbs-content-info-photo {
	float: left;
	width: 85px;
}
.bbs-content-info-detail {
	width: 710px;
	min-height: 150px;
	background-color: #FFF;
	overflow: hidden;
}
.bbs-content-info-photo-img {
	width: 65px;
	height: 65px;
}
.bbs-content-info-detail-title {
	overflow: hidden;
}
.bbs-content-info-detail-title-nickName {
	float: left;
	margin-left: 20px;
	margin-top: 20px;
	width: 550px;
	color: #666666;
	height: 25px;
	line-height: 25px;
}
.bbs-content-info-detail-title-createDate {
	float: left;
	margin-left: 10px;
	margin-top: 20px;
	width: 130px;
	color: #666666;
	height: 25px;
	line-height: 25px;
}
.bbs-content-info-detail-title-line {
	display: -moz-inline-box;
	display: inline-block;
	width: 25px;
	height: 2px;
	background-color: #666666;
 	position: relative;
	top: -3px;
}
.bbs-content-info-detail-body {
	width: 665px;
	margin-left: 20px;
	margin-top: 10px;
	min-height: 57px;
}
.bbs-content-info-detail-label {
	overflow: hidden;
	margin-left: 20px;
	margin-top: 10px;
	margin-bottom: 20px;
	color: #919191;
}
.bbs-content-info-detail-label-typeName {
	display: -moz-inline-box;
	display: inline-block;
	color: #FFF;
	background-color: #919191;
	margin-left: 5px;
	width: 40px;
	text-align: center;
}
.bbs-content-info-detail-label-space {
	display: -moz-inline-box;
	display: inline-block;
	width: 400px;
}
.bbs-content-info-detail-label-look {
	display: -moz-inline-box;
	display: inline-block;
	width: 110px;
	color: #919191;
}
.bbs-content-info-detail-label-reply {
	display: -moz-inline-box;
	display: inline-block;
	width: 90px;
	color: #919191;
}
.bbs-content-info-reply {
	background-color: #F8F8F8;
	width: 710px;
	margin-top: 2px;
	overflow: hidden;
}
.bbs-content-info-body {
	overflow: hidden;
	width: 710px;
	float: left;
}
.bbs-content-info-reply-input {
	width: 625px;
	border: solid 1px #AFAFAF;
	height: 30px;
	padding: 5px;
	background-color: transparent;
}
.bbs-content-info-reply-button {
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
.bbs-content-info-reply-div {
	width: 100%;
	margin-top: 10px;
	margin-left: 10px;
}
.bbs-content-info-reply-detail {
	overflow: hidden;
	width: 670px;
	margin-left: 20px;
}
.bbs-content-info-reply-detail-line {
	margin-top: 20px;
	width: 100%;
	height: 1px;
	background-color: #AFAFAF;
}
.bbs-content-info-reply-detail-list-data {
	margin-top: 20px;
	width: 100%;
	overflow: hidden;
}
.bbs-content-info-reply-detail-list-data-img {
	vertical-align: top;
	width: 70px;
	float: left;
}
.bbs-content-info-reply-detail-list-data-img img {
	width: 55px;
	height: 55px;
}
.bbs-content-info-reply-detail-list-data-content {
	width: 600px;
	overflow: hidden;
	float: left;
}
.bbs-content-info-reply-detail-list-data-content-ownerName {
	width: 300px;
	text-align: left;
	display: -moz-inline-box;
	display: inline-block;
}
.bbs-content-info-reply-detail-list-data-content-createDate {
	width: 300px;
	text-align: right;
	display: -moz-inline-box;
	display: inline-block;
}
.bbs-content-info-reply-detail-list-data-content-value {
	margin-top: 10px;
}
.bbs-content-info-reply-detail-list-data-content-reply {
	margin-top: 10px;
}
.bbs-content-info-reply-detail-list-line-sub {
	margin-top: 10px;
	width: 100%;
	height: 1px;
	background-color: #AFAFAF;
}
.bbs-content-info-reply-detail-list-data-sub {
	margin-top: 10px;
	width: 100%;
	overflow: hidden;
}
.bbs-content-info-reply-detail-list-data-sub-img {
	vertical-align: top;
	width: 60px;
	float: left;
}
.bbs-content-info-reply-detail-list-data-sub-img img {
	width: 45px;
	height: 45px;
}
.bbs-content-info-reply-detail-list-data-sub-content {
	width: 540px;
	overflow: hidden;
	float: left;
}
.bbs-content-info-reply-detail-list-data-sub-content-ownerName {
	width: 240px;
	text-align: left;
	display: -moz-inline-box;
	display: inline-block;
}
.bbs-content-info-reply-detail-list-data-sub-content-createDate {
	width: 300px;
	text-align: right;
	display: -moz-inline-box;
	display: inline-block;
}
.bbs-content-info-reply-detail-list-data-sub-content-value {
	margin-top: 10px;
}
.bbs-content-info-reply-detail-floot {
	width: 100%;
	height: 20px;
}
.bbs-content-info-reply-detail-list-data-content-click {
	width: 600px;
	overflow: hidden;
	margin-top: 10px;
}
.bbs-content-info-reply-detail-list-data-content-input {
	background-color: transparent;
	width: 532px;
	border: solid 1px #AFAFAF;
	height: 30px;
}
body {
    background-color: #EDEDEF;
}
</style>
<div class="bbs-header-content">
	<div class="bbs-header-content-title"><fmt:message key="lms.bbs"/></div>
	<div class="bbs-header-content-line"></div>
	<div class="bbs-header-content-note"><fmt:message key="lms.bbsExplain1"/></div>
	<div class="bbs-header-content-note"><fmt:message key="lms.bbsExplain2"/></div>
</div>
<div class="bbs-header"></div>
<div id="bbs-content" class="bbs-content">
	<div class="bbs-content-self">
		<img id="bbs-content-self-img" class="bbs-content-self-img" src=""/>
		<span class="bbs-content-self-add" onclick="addBbs()"><fmt:message key="lms.publishPost"/></span>
		<span id="bbs-content-self-nickName" class="bbs-content-self-nickName"></span>
	</div>
	<div id="bbs-content-data" class="bbs-content-data"></div>
</div>
<div id="bbs-content-self-mask" class="bbs-content-self-mask"></div>
<div></div>
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
<script type="text/javascript">
	var fmtTitle = '<fmt:message key="lms.title"/>';
	var fmtTags = '<fmt:message key="lms.tags"/>';
	var fmtContent = '<fmt:message key="lms.content"/>';
	var fmtMakeComments = '<fmt:message key="lms.makeComments"/>';
	var fmtBack = '<fmt:message key="lms.back"/>';
	var fmtPageview = '<fmt:message key="lms.pageview"/>';
	var fmtComment = '<fmt:message key="lms.comment"/>';
	var fmtReplyYourOpinion = '<fmt:message key="lms.replyYourOpinion"/>';
	var fmtReply = '<fmt:message key="lms.reply"/>';
</script>
<script src="${pageContext.request.contextPath}/js/ui/bbs/bbsInfo.js?version=${version}"></script>
<script src="${pageContext.request.contextPath}/js/common/login_username.js?version=${version}"></script>
</html>