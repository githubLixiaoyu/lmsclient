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
 table label{
 	float: right;
 }
 td{
 	padding:5px;
 }
</style>

</head>
<body>
	<div>
		<div class="col-md-12" style="overflow-y: auto;overflow-x: hidden; height: 180px">
		
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.detailInfo"/>
					</div>
				</div>
				<div class="portlet-body">
					<input id="contentId" type="hidden"/>
					<textarea id="content" name="content" style="width: 100%; height: 80px;" class="form-control"></textarea>
				</div>
			</div>
		</div>

		<div class="modal-footer" style="padding: 15px 32px 0px 15px;">
			<button id="saveBtn" type="button" class="btn blue btn-sm"
				style="margin-top: 15px;"><fmt:message key="lms.save"/></button>
			<button id="baseWinFalseBut" type="button" class="btn default btn-sm"
				data-dismiss="modal" style="margin-top: 15px;"><fmt:message key="lms.close"/></button>
		</div>
	</div>
</body>
<sitemesh:script>
<script src="${pageContext.request.contextPath}/js/teacher/question/content_dlg.js?version=${version}"></script>
<script>
	//获取父页面对象
	var winPar  = window.parent;
</script>
</sitemesh:script>
</html>