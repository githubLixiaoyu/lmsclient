<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title></title>
	<style type="text/css">
		#searchForm td {
			white-space:nowrap; 
		}
		tbody td{
			padding-left: 5px !important;
		}
	</style>
</head>
<body>
	<div class="row">
		<div class="col-md-12" style="margin: 5px 150px;">
			<button type="button" id="setPaperBtn" class="btn btn-success">
				<fmt:message key="lms.creatConstantTestpaper"/> 
			</button>
			<button type="button" id="randomPaperBtn" class="btn btn-success" style="margin-left: 20px;">
				<fmt:message key="lms.creatRandomTestpaper"/>
			</button>
		</div>
		<div class="modal-footer" style="padding: 15px 32px 0px 15px;border: 0px;">
			<button id="cancelBtn" type="button" class="btn default btn-sm"
				data-dismiss="modal" style="margin-top: 15px;"><fmt:message key="lms.close"/></button>
		</div>
	</div>
	
</body>
<sitemesh:script>
<script>
//固定试卷创建
$("#setPaperBtn").on("click", function(){
	var dataStr = "?paperId=''&papertype=1";
	window.location.href= server+"/pages/teacher/newtestpaper/newtestpaperEdit.jsp"+dataStr;
})
//随机试卷创建
$("#randomPaperBtn").on("click", function(){
	var dataStr = "?paperId=''&papertype=2";
	window.location.href= server+"/pages/teacher/newtestpaper/newtestpaperEdit.jsp"+dataStr;
})
</script>
</sitemesh:script>
</html>