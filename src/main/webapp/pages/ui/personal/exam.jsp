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
.exam-header {
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
.exam-header-content {
 	position: absolute;
	top: 130px;
    width: 100%;
    height: 100px;
    text-align: center;
}
.exam-header-content-title {
    color: #FFF;
    font-size: 40px;
}
.exam-content {
	min-width: 1024px;
	overflow: hidden;
}
.exam-content-body {
	width: 1030px;
	overflow: hidden;
	margin: 0 auto;
}
.exam-content-explain {
	width: 100%;
	overflow: hidden;
}
.exam-content-explain-left {
    float: left;
    height: 100%;
    text-align: left;
    width: 300px;
	vertical-align: top;
}
.exam-content-explain-centre {
    float: left;
    height: 260px;
    width: 1px;;
	background-color: #666666;
	margin-top: 140px;
	margin-left: 50px;
}
.exam-content-explain-right {
    float: left;
    height: 100%;
    text-align: left;
    width: 600px;
	color: #666666;
	font-size: 18px;
	line-height: 40px;
	margin-top: 130px;
	margin-left: 50px;
}
.exam-content-explain-left-body {
	margin-top: 100px;
}
.exam-content-explain-left-body-label {
	color: #666666;
	font-size: 18px;
}
.exam-content-explain-left-body-examtime {
	color: #CBA67C;
	font-size: 60px;
}
.exam-content-explain-left-body-line {
	width: 100%;
	height: 1px;
	background-color: #666666;
	margin-top: 20px;
}
.exam-content-explain-left-body-examname {
	color: #666666;
	font-size: 18px;
	margin-top: 40px;
}
.exam-content-explain-left-body-examkhzl {
	color: #666666;
	font-size: 18px;
	margin-top: 30px;
}
.exam-content-button {
	width: 125px;
	height: 40px;
	background-color: #CBA67C;
	color: #FFFFFF;
	font-size: 18px;
	text-align: center;
	line-height: 40px;
	margin: 50px auto;
	cursor: pointer;
}
.exam-content-left {
	float: left;
	width: 20%;
	overflow: hidden;
	text-align: center;
}
.exam-content-centre {
	float: left;
	width: 60%;
	overflow: hidden;
	text-align: center;
	background-color: #FFF;
	min-height: 500px;
}
.exam-content-right {
	float: left;
	width: 20%;
	overflow: hidden;
	text-align: center;
}
.exam-content-left-body {
	margin-top: 30px;
	margin-bottom: 20px;
}
.exam-content-left-body-label {
	color: #666666;
	font-size: 16px;
}
.exam-content-left-body-remainingTime {
	color: #CBA67C;
	font-size: 60px;
}
.exam-content-left-body-line {
	width: 180px;
	height: 1px;
	background-color: #666666;
	margin: 0 auto;
}
.exam-content-left-body-examname {
	color: #666666;
	font-size: 16px;
	margin-top: 40px;
}
.exam-content-left-body-examkhzl {
	color: #666666;
	font-size: 16px;
	margin-top: 30px;
}
.exam-content-left-button {
	width: 100px;
	height: 40px;
	background-color: #CBA67C;
	color: #FFFFFF;
	font-size: 16px;
	text-align: center;
	line-height: 40px;
	margin: 40px auto 0px auto;
	display: none;
	cursor: pointer;
}
.exam-content-left-button-remark {
	width: 100px;
	height: 40px;
	background-color: transparent;
	border-color: #CBA67C;
	border-style: solid;
	border-width: 2px;
	color: #CBA67C;
	font-size: 16px;
	text-align: center;
	line-height: 36px;
	margin: 40px auto 0px auto;
	display: none;
	cursor: pointer;
}
.exam-content-centre-question {
	width: 100%;
	overflow: hidden;
}
.exam-content-centre-line {
	width: 95%;
	height: 1px;
	margin: 10px auto;
	background-color: #666666;
}
.exam-content-centre-button {
	width: 100%;
	overflow: hidden;
	margin-top: 10px;
	margin-bottom: 20px;
}
.exam-content-centre-question-content {
	float: left;
	width: 88%;
	text-align: left;
	overflow: hidden;
	margin-top: 20px;
	margin-left: 20px;
	font-size: 16px;
	color: #666666;
}
.exam-content-centre-question-state {
	float: left;
	width: 26px;
	height: 26px;
	margin-top: 20px;
	margin-left: 20px;
	cursor: pointer;
}
.exam-content-centre-question-option {
	width: 85%;
	overflow: hidden;
	margin-left: 40px;
	font-size: 16px;
	text-align: left;
	cursor: pointer;
}
.exam-content-centre-question-body {
	width: 100%;
	overflow: hidden;
}
.exam-content-centre-question-button {
	text-align: right;
	width: 90%;
	overflow: hidden;
	margin-top: 20px;
}
.exam-content-centre-question-button-prev {
	display: -moz-inline-box;
	display: inline-block;
	width: 120px;
	height: 28px;
    background: url("${pageContext.request.contextPath}/images/exam/prev.png") no-repeat left top;
	cursor: pointer;
}
.exam-content-centre-question-button-next {
	display: -moz-inline-box;
	display: inline-block;
	width: 120px;
	height: 28px;
    background: url("${pageContext.request.contextPath}/images/exam/next.png") no-repeat left top;
	cursor: pointer;
}
.exam-content-right-remarks {
	width: 80%;
	overflow: hidden;
	margin-top: 20px;
	margin-left: 20px;
	text-align: left;
	font-size: 16px;
	line-height: 25px;
	color: #666666;
}
.exam-content-centre-button-body {
	width: 93%;
	overflow: hidden;
	margin: 0 auto;
}
.exam-content-centre-button-body-btn {
	float: left;
	width: 50px;
	height: 30px;
	margin-top: 10px;
	margin-left: 10px;
	cursor: pointer;
}
.exam-content-centre-button-body-btn-span {
	font-size: 18px;
	position: relative;
	text-align: center; 
	display: block;
	top: 5px;
	left: -2px;
}
.exam-content-centre-score-label {
	margin-top: 80px;
	margin-left: 70px;
	font-size: 18px;
	color: #666666;
	text-align: left;
}
.exam-content-centre-score-div {
	width: 100%;
	overflow: hidden;
	margin-top: 80px;
	margin-bottom: 80px;
}
.exam-content-centre-score-value {
	color: #666666;
	font-size: 80px;
}
.exam-content-centre-score-span {
	color: #666666;
	font-size: 16px;
}
.exam-content-centre-table-title{
	width:90%; 
	height:50px; 
	border:0px; 
	cellspacing:0px; 
	cellpadding:0px;
	margin-top: 40px;
	font-size: 20px;
	margin: 30px auto 20px auto;
}
.exam-content-centre-table-body{
	width:90%; 
	height:100px; 
	cellspacing:0px; 
	cellpadding:0px;
	font-size: 16px;
	color: #373737;
	margin: 0 auto 30px auto;
}
.exam-content-centre-table-body-td {
	border: solid 1px #AFAFAF;
}
.exam-content-centre-table-body-tr {
	line-height: 40px;
}
.exam-content-centre-table-line-color{
	background-color: #dedede;
}
body {
    background-color: #FFF;
}
</style>
<div class="exam-header-content">
	<div id="exam-header-content-title" class="exam-header-content-title"></div>
</div>
<div class="exam-header"></div>
<div id="exam-content" class="exam-content"></div>
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
	var examId = <%=request.getParameter("examId")%>;
	var userId = <%=request.getParameter("userId")%>;
	var type = <%=request.getParameter("type")%>;
	var isVr = <%=request.getParameter("isVr")%>;
	var fmtTestTime = '<fmt:message key="lms.testTime"/>';
	var fmtMin = '<fmt:message key="lms.min"/>';
	var fmtNameOfTestPaper = '<fmt:message key="lms.nameOfTestPaper"/>';
	var fmtTestSort = '<fmt:message key="lms.testSort"/>';
	var fmtTestType = '<fmt:message key="lms.testType"/>';
	var fmtTheory = '<fmt:message key="lms.theory"/>';
	var fmtTestIntroduction = '<fmt:message key="lms.testIntroduction"/>';
	var fmtStartTest = '<fmt:message key="lms.startTest"/>';
	var fmtUsedTime = '<fmt:message key="lms.usedTime"/>';
	var fmtRemainingTime = '<fmt:message key="lms.remainingTime"/>';
	var fmtTestPaperSubmission = '<fmt:message key="lms.testPaperSubmission"/>';
	var fmtExit = '<fmt:message key="lms.exit"/>';
	var fmtQuestionAnalysis = '<fmt:message key="lms.questionAnalysis"/>';
	var fmtCorrectAnswer = '<fmt:message key="lms.correctAnswer"/>';
	var fmtTestResult = '<fmt:message key="lms.testResult"/>';
	var fmtScoreSmall = '<fmt:message key="lms.scoreSmall"/>';
	var fmtTitleCourse = '<fmt:message key="lms.titleCourse"/>';
	var fmtTitleCoursePoint = '<fmt:message key="lms.titleCoursePoint"/>';
	var fmtTitleTask = '<fmt:message key="lms.titleTask"/>';
	var fmtTitleResult = '<fmt:message key="lms.titleResult"/>';
</script>
<script src="${pageContext.request.contextPath}/js/ui/personal/exam.js?version=${version}"></script>
<script src="${pageContext.request.contextPath}/js/common/login_username.js?version=${version}"></script>
</html>