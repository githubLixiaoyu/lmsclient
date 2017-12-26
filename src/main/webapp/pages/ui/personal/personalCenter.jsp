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
.personal-header {
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
.personal-header-content {
 	position: absolute;
	top: 130px;
    width: 100%;
    height: 150px;
    text-align: center;
}
.personal-header-content-title {
    color: #FFF;
    font-size: 40px;
    line-height: 60px;
}
.personal-header-content-line {
    background-color: #CBA67C;
    width: 95px;
    height: 5px;
    margin: 10px auto;
}
.personal-header-content-note {
    color: #FFF;
    font-size: 12px;
    margin-top: 5px;
}
.personal-type {
	width: 100%;
	background-color: #FFF;
	overflow:hidden;
	background-color: #eeeeee;
	min-height:100%;height:auto;
}
.personal-type-key {
	float: left;
	height: 100%;
	text-align: center;
	width: 250px;
}
.personal-type-key-content {
	float: left;
	width: 70%;
	text-align:left;
	margin-top: 50px;
	margin-left: 30%;
	line-height: 40px;
}
.personal-type-content {
	float: left;
	background-color: #FFF;
	min-height: 630px;
	width: calc(100% - 250px);
}
.personal-type-content-tag {
	width: 100%;
	margin-left: 50px;
	margin-top: 50px;
}
.personal-type-content-tag-span {
	color: #373737;
    font-size: 16px;
}
.personal-type-content-tag-span-zh {
	color: #373737;
    font-size: 16px;
    margin-left: 40px;
    cursor: pointer;
}
.personal-type-content-tag-span-en {
	color: #373737;
    font-size: 16px;
    margin-left: 40px;
    cursor: pointer;
}
.personal-type-content-tag-span-all {
	color: #373737;
    font-size: 16px;
    margin-left: 40px;
    cursor: pointer;
}
.personal-type-content-info {
	width: 100%;
}
.personal-type-key-content-label {
	color: #373737;
	text-decoration: none;
}
.personal-type-key-content a:hover {
	color: #d1aa7d;
	text-decoration: none;
}
.course-deta-content {
	float: left;
	width: 300px;
	height: 200px;
}
.personal-type-content-first-title {
	margin-top: 40px;
	margin-left: 50px;
	font-size: 18px;
	color: #373737;
}
.personal-type-content-second {
	margin-left: 80px;
	margin-top: 30px;
}
.personal-type-content-second-title {
	font-size: 15px;
	color: #373737;
}
.personal-type-content-first {
	overflow:hidden;
}
.personal-type-content-second-img {
	margin-top: 10px;
}
.personal-type-content-second-img {
	margin-top: 10px;
	width: 259px;
	height: 144px;
}
.personal-type-content-first-title {
	margin-top: 40px;
	margin-left: 50px;
	font-size: 18px;
	color: #373737;
}
.personal-type-content-second {
	margin-left: 80px;
	margin-top: 30px;
}
.course-deta-content {
	float: left;
	width: 300px;
	height: 220px;
}
.personal-type-content-second-title {
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
.personal-type-content-second-img {
	margin-top: 10px;
	width: 259px;
	height: 144px;
}
.personal-type-content-second-learn-num {
	margin-top: 10px;
	display: -moz-inline-box;
	display: inline-block;
	width: 200px;
	text-align: right;
	color: #CBA389;
}
.personal-type-content-second-learn-label {
	margin-top: 10px;
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
.course-type-content-second-comment {
	line-height: 30px;
}
.course-type-content-second-comment-spac {
	display: -moz-inline-box;
	display: inline-block;
	width: 70px; 
}
.praise {
	color: #C5A781;
}
.hand {
	cursor: pointer;
}
.personal-info-img-label {
	margin: auto;
	font-size: 18px;
	margin-top: 50px;
	width: 225px;
	left: 1%;
	right: 1%;
}
.personal-info-img-div {
	margin: auto;
	width: 225px;
	left: 1%;
	right: 1%;
}
.personal-info-img {
	margin: auto;
	width: 225px;
	height: 225px;
	margin-top: 15px;
	left: 1%;
	right: 1%;
}
.personal-info-level {
	margin: auto;
	width: 225px;
	left: 1%;
	right: 1%;
}
.personal-info-nick-label {
	width: 370px;
	margin: auto;
	left: 1%;
	right: 1%;
	font-size: 18px;
	margin-top: 40px;
}
.personal-info-nick-input-div {
	width: 370px;
	margin: auto;
	left: 1%;
	right: 1%;
}
.personal-info-pwd-label {
	width: 370px;
	margin: auto;
	left: 1%;
	right: 1%;
	font-size: 18px;
	margin-top: 30px;
}
.personal-info-pwd-input-div {
	width: 370px;
	margin: auto;
	left: 1%;
	right: 1%;
}
.personal-info-nick-label-span {
	display: -moz-inline-box;
	display: inline-block;
	width: 200px; 
	text-align: left;
}
.personal-info-nick-label-a {
	display: -moz-inline-box;
	display: inline-block;
	width: 170px; 
	text-align: right;
}
.personal-info-nick-input {
	width: 370px;
	border: solid 2px #AFAFAF;
	height: 30px;
	margin-top: 10px;
}
.personal-info-pwd-label-span {
	display: -moz-inline-box;
	display: inline-block;
	width: 200px; 
	text-align: left;
}
.personal-info-pwd-input {
	width: 370px;
	border: solid 2px #AFAFAF;
	height: 30px;
	margin-top: 10px;
}
.personal-info-nick-label a {
	color: #ACACAC;
	text-decoration: none;
}
.personal-info-nick-label a:hover {
	color: #CBA389;
	text-decoration: none;
}
.personal-info-pwd-label a {
	color: #ACACAC;
	text-decoration: none;
}
.personal-info-pwd-label a:hover {
	color: #CBA389;
	text-decoration: none;
}
.personal-info-photoImage {
	display: none;
}
.personal-ranking {
	overflow:hidden;
	width: 100%;
	min-width: 850px;
}
.personal-ranking-self {
	float: left;
	width: 25%;
	text-align: center;
}
.personal-ranking-self-img {
	margin-top: 40px;
	margin-bottom: 50px;
	width: 200px;
	height: 200px;
}
.personal-ranking-self-span {
	color: #666666;
	font-size: 80px;
}
.personal-ranking-line {
	float: left;
	background-color: #666666;
	width: 2px;
	height: 550px;
	margin-top: 100px;
}
.personal-ranking-content {
	float: left;
	margin-top: 20px;
	width: 70%;
}
.personal-ranking-content-table {
	width: 100%;
}
.personal-ranking-content-td {
	width: 20%;
	line-height: 60px;
	font-size: 18px;
	text-align: center;
}
.personal-ranking-content-title {
	width: 100%;
}
.personal-ranking-content-column {
	width: 100%;
}
.personal-ranking-content-data {
	width: 100%;
	height: 530px;
	overflow-y: auto;
}
.personal-ranking-content-detail {
	width: 100%;
	overflow: hidden;
}
.personal-ranking-content-td-img {
	width: 50px;
	height: 50px;
}
.personal-type-key-qrcode {
	width: 150px;
	height: 150px;
	margin-top: 20px
}
.personal-exam-self-no {
	color: #666666;
	font-size: 80px;
	font-family: Arno Pro;
}
.personal-exam-self-yes {
	color: #666666;
	font-size: 40px;
	font-family: Arno Pro;
}
.personal-exam-self-span {
	color: #666666;
	font-size: 15px;
}
.personal-exam-content-wk {
	width: 20%;
	line-height: 60px;
	text-align: center;
}
.personal-exam-content-yk {
	width: 20%;
	line-height: 60px;
	text-align: left;
}
.personal-exam-content-span {
	font-size: 18px;
	color: #666666;
	cursor: pointer;
}
.personal-exam-content-data {
	width: 100%;
	height: 550px;
	overflow-y: auto;
	margin-top: 20px;
}
.personal-exam-content-table-data {
	width: 100%;
	overflow: hidden;
}
.personal-exam-empty {
	width: 100%;
	height: 10px;
}
.personal-exam-content-table-data-div {
	width: 100%;
	height: 75px;
}
.personal-exam-content-table-data-empty {
	width: 5%;
	height: 75px;
	float: left;
}
.personal-exam-content-table-data-score {
	width: 100px;
	height: 75px;
	float: left;
	background-color: #CFCFCF;
	text-align: center;
	line-height: 75px;
	cursor: pointer;
}
.personal-exam-content-table-data-detail {
	width: 75%;
	height: 75px;
	float: left;
	background-color: #EEEEEE;
	vertical-align: middle;
	cursor: pointer;
}
.personal-exam-content-table-data-score-value {
	color: #FFFFFF;
	font-size: 60px;
}
.personal-exam-content-table-data-score-label {
	color: #FFFFFF;
	font-size: 20px;
}
.personal-exam-content-table-data-detail-header {
	width: 100%;
	height: 40px;
}
.personal-exam-content-table-data-detail-header-examname {
	width: 60%;
	height: 40px;
	text-align: left;
	float: left;
	line-height: 40px;
	color: #666666;
	font-size: 18px;
}
.personal-exam-content-table-data-detail-header-examtime {
	width: 40%;
	height: 40px;
	text-align: right;
	float: left;
	line-height: 40px;
	color: #666666;
	font-size: 15px;
}
.personal-exam-content-table-data-detail-line {
	width: 95%;
	height: 1px;
	background-color: #CFCFCF;
	margin: 0 auto;
}
.personal-exam-content-table-data-detail-footer {
	float: left;
	width: 50%;
	height: 34px;
	text-align: left;
	line-height: 34px;
	color: #666666;
	font-size: 15px;
}
.personal-exam-content-table-data-qk {
	width: 100px;
	height: 75px;
	float: left;
    background-color: red;
	text-align: center;
	line-height: 75px;
	cursor: pointer;
}
.personal-exam-content-table-data-qk-span {
	color: #FFFFFF;
	font-size: 30px;
}
.personal-exam-content-table-data-ks {
	width: 100px;
	height: 75px;
	float: left;
    background-color: #CBA67C;
	text-align: center;
	line-height: 75px;
	cursor: pointer;
}
.personal-exam-content-table-data-ks-span {
	color: #FFFFFF;
	font-size: 30px;
}
.personal-exam-content-table-data-dk {
	width: 100px;
	height: 75px;
	float: left;
    background-color: #CFCFCF;
	text-align: center;
	line-height: 75px;
	cursor: pointer;
}
.personal-exam-content-table-data-dk-span {
	color: #FFFFFF;
	font-size: 30px;
}
body {
    background-color: #FFF;
}
</style>
<div class="personal-header-content">
	<div class="personal-header-content-title"><fmt:message key="lms.personalCenter"/></div>
	<div class="personal-header-content-line"></div>
	<div class="personal-header-content-note"><fmt:message key="lms.personalCenterExplain1"/></div>
	<div class="personal-header-content-note"><fmt:message key="lms.personalCenterExplain2"/></div>
</div>
<div class="personal-header"></div>
<div class="personal-type">
	<div class="personal-type-key">
		<div id="personal-type-key" class="personal-type-key-content">
			<span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;<fmt:message key="lms.personalCenter"/><br />
			<span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;<a class="personal-type-key-content-label" href="javascript:void(myInfo())"><fmt:message key="lms.personalInformation"/></a><br />
			<span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;<a class="personal-type-key-content-label" href="javascript:void(myCourse())"><fmt:message key="lms.myCourse"/></a><br />
			<span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;<a class="personal-type-key-content-label" href="javascript:void(myRanking())"><fmt:message key="lms.myRanking"/></a><br />
			<span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;<a class="personal-type-key-content-label" href="javascript:void(myExam())"><fmt:message key="lms.myTest"/></a><br />
<!-- 			<span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;<a class="personal-type-key-content-label" href="javascript:void(myNews())"><fmt:message key="lms.myNews"/></a><br /> -->
		</div>
	</div>
	<div id="personal-type-content" class="personal-type-content"></div>
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
<script type="text/javascript">
	var personalFlag = <%=request.getParameter("personalFlag")%>;
	var fmtCurrentAvatar = '<fmt:message key="lms.currentAvatar"/>';
	var fmtNickName = '<fmt:message key="lms.nickName"/>';
	var fmtPassword = '<fmt:message key="lms.password"/>';
	var fmtModify = '<fmt:message key="lms.modify"/>';
	var fmtNationalRankingList = '<fmt:message key="lms.nationalRankingList"/>';
	var fmtRanking = '<fmt:message key="lms.ranking"/>';
	var fmtAvatar = '<fmt:message key="lms.avatar"/>';
	var fmtLevel = '<fmt:message key="lms.level"/>';
	var fmtScore = '<fmt:message key="lms.score"/>';
	var fmtUntested = '<fmt:message key="lms.untested"/>';
	var fmtTested = '<fmt:message key="lms.tested"/>';
	var fmtToBeTest = '<fmt:message key="lms.toBeTest"/>';
	var fmtTestAbsent = '<fmt:message key="lms.testAbsent"/>';
	var fmtStart = '<fmt:message key="lms.start"/>';
	var fmtSort = '<fmt:message key="lms.sort"/>'; 
	var fmtTestType = '<fmt:message key="lms.testType"/>'; 
	var fmtVideo = '<fmt:message key="lms.video"/>';
	var fmtCourseware = '<fmt:message key="lms.courseware"/>';
	var fmtScoreSmall = '<fmt:message key="lms.scoreSmall"/>';
	var fmtClassesHadLearned = '<fmt:message key="lms.classesHadLearned"/>';
	var fmtThreeDimensional = '<fmt:message key="lms.threeDimensional"/>';
	var fmtTheory = '<fmt:message key="lms.theory"/>';
	var fmtCourseLanguage = '<fmt:message key="lms.courseLanguage"/>';
	var fmtCourseChinese = '<fmt:message key="lms.courseChinese"/>';
	var fmtCourseEnglish = '<fmt:message key="lms.courseEnglish"/>';
	var fmtCourseAll = '<fmt:message key="lms.courseAll"/>';
</script>
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-file-upload/js/ajaxfileupload.js?version=${version}"></script>
<script src="${pageContext.request.contextPath}/js/ui/personal/personalCenter.js?version=${version}"></script>
<script src="${pageContext.request.contextPath}/js/common/login_username.js?version=${version}"></script>
</html>