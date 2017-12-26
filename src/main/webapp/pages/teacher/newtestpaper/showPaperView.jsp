<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title><fmt:message key="lms.testpaperPreview"/></title>
<style type="text/css">
p {
	display: inline;
}
#ExamName{
	background-color: #ffffff;
    color: #ff0000;
    font-family: 黑体;
    font-size: 20pt;
    font-weight: bold;
    height: 60px;
    line-height: 60px;
    margin: 0 auto;
    text-align: center;
    width: 100%;
}
.QBody{
    color: #000000;
    height: auto;
    line-height: 24px;
    margin: 5px 0;
    padding: 10px 15px;
}
.QName{
	border-bottom: 1px dashed #c9c9c9;
    height: auto;
    line-height: 30px;
}
.QOption{
	padding-left:10px;
    line-height: 30px;
}
</style>
</head>
<body>
	<div id="body">
	</div>
</body>
<sitemesh:script>
<script type="text/javascript">
var paperId = <%=request.getParameter("paperid")%>;
var papername = <%=request.getParameter("papername")%>;
//第几题
var fmtQuestionNo = '<fmt:message key="lms.questionNo"/>';
//分
var fmtQuestionScore = '<fmt:message key="lms.questionScore"/>';
</script>
<script src="${pageContext.request.contextPath}/js/teacher/newtestpaper/showPaperView.js?version=${version}"></script>
</sitemesh:script>
</html>
