<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title><fmt:message key="lms.testpaperInfo"/></title>
<style type="text/css">
 table label{
 	float: right;
 	margin-bottom: 0px;
 }
 td{
 	padding:5px;
 }
</style>

</head>
<body>
<div class="modal fade" id="portlet-upload-dlg" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">Modal Title</h4>
			</div>
			<div class="modal-body">
				 Modal body goes here
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
	<div>
		<div class="col-md-12" style="overflow-y: auto;overflow-x: hidden;">
		
			<div class="portlet box blue-hoki nomargin">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.fillFollowing"/>
					</div>
					
				</div>
				<div class="portlet-body">
					
					<table width="100%">
						<tr>
							<td width="15%"><label><fmt:message key="lms.testpaperName"/>:</label></td>
							<td width="75%" colspan="3">
							<input class="form-control" id="testpapername"
								name="testpapername" type="text" style="width: 100%;" maxlength="255"></td>
							<td width="10%"></td>
						</tr>
						<tr>
							
							<td width="15%"><label><fmt:message key="lms.testTemplate"/>:</label></td>
							<td width="25%">
							     <input id="testtemplate" name="testtemplate" class="form-control  select2" type="text" style="width: 100%;"/></td>
							</td>
							<td width="15%"><label><fmt:message key="lms.testType"/>:</label></td>
							<td width="25%">
								<input id=openType name="openType" class="form-control  select2" type="text" style="width: 100%;"/></td>
						    <td width="10%"></td>
						</tr>
						<tr>
							<td width="15%"><label><fmt:message key="lms.testTime"/>:</label></td>
							<td width="75%" colspan="3">
								<input class="form-control" id="testtime"
									name="testtime" type="text" style="width: 200px;" maxlength="20">
							</td>
							<td width="10%"></td>
						</tr>
						<tr>
							<td width="15%"><label><fmt:message key="lms.testpaperDescription"/>:</label></td>
							<td width="75%" colspan="3">
								<textarea id="description" name="description"
										style="width: 100%; height: 100px;" class="form-control" maxlength="255"></textarea>
							</td>
							<td width="10%"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div class="modal-footer" style="padding: 15px 32px 0px 15px;">
<!-- 			<button id="completeBtn" type="button" class="btn blue btn-sm" -->
<!-- 				style="margin-top: 15px;">完成</button> -->
<!-- 			<button id="testBut" type="button" class="btn default btn-sm" -->
<!-- 				data-dismiss="modal" style="margin-top: 15px;">测试</button> -->
<input type="hidden" id="saveBtn" class="btn btn-success" style="margin-top: 15px;"value="<fmt:message key="lms.save"/>">
			<button id="returnBut" type="button" class="btn default btn-sm"
				data-dismiss="modal" style="margin-top: 15px;"><fmt:message key="lms.return"/></button>
		</div>
	</div>
</body>
<sitemesh:script>
<script src="${pageContext.request.contextPath}/js/teacher/vrnewtestpaper/newtestpaperEdit.js?version=${version}"></script>
<script>
var paperId = <%=request.getParameter("paperId")%>;
//不能为空
var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
//新增试卷
var fmtTestpaperName = '<fmt:message key="lms.testpaperName"/>';
//考试时长
var fmtTestTime = '<fmt:message key="lms.testTime"/>';
//请选择
var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
//应是正整数
var fmtSignlessInteger = '<fmt:message key="lms.signlessInteger"/>';
//PC版
var fmtPC = '<fmt:message key="lms.pc"/>';
//VR版
var fmtVR = '<fmt:message key="lms.vr"/>';
//HTC简化版
var fmtHTCsimple = '<fmt:message key="lms.htcsimple"/>';
//HTC高配版
var fmtHTChigh = '<fmt:message key="lms.htchigh"/>';
</script>
</sitemesh:script>
</html>