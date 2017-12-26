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
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.basicInfo"/>
					</div>
					<div class="actions" style="float: right;">
						<input type="hidden" id="saveBtn" class="btn btn-success" value="<fmt:message key="lms.save"/>">
						<input type="hidden" id="editBtn" class="btn btn-success" value="<fmt:message key="lms.edit"/>">
					</div>
				</div>
				<div class="portlet-body">
					<input type="hidden" id="papertype" value="<%=request.getParameter("papertype")%>">
					<table width="100%">
						<tr>
							<td width="15%"><label><fmt:message key="lms.testpaperName"/>:</label></td>
							<td id="td_papername" width="75%" colspan="3"></td>
							<td width="10%"></td>
						</tr>
						<tr>
							<td width="15%"><label><fmt:message key="lms.testaperCategory"/>:</label></td>
							<td id="td_categoryid" width="25%"></td>
							<td width="15%"><label><fmt:message key="lms.questionDifficulty"/>:</label></td>
							<td id="td_difficult" width="25%"></td>
							<td width="10%"></td>
						</tr>
						<tr>
							<td width="15%"><label><fmt:message key="lms.forPeople"/>:</label></td>
							<td id="td_forpaper" width="75%" colspan="3"></td>
							<td width="10%"></td>
						</tr>
						<tr>
							<td width="15%"><label><fmt:message key="lms.testpaperDescription"/>:</label></td>
							<td id="td_remark" width="75%" colspan="3"></td>
							<td width="10%"></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.questionTypeInfo"/>
					</div>
				</div>
				<div class="portlet-body myform nopadding">
					<div class="portlet-body">
						<div class="table-responsive">
							<div class="tabbable-custom" style="margin-bottom: 0px;">
								<table id="listTable" class="table table-hover">
									<thead>
										<tr>
											<th width="12%"><fmt:message key="lms.select"/></th>
											<th width="22%"><fmt:message key="lms.paperQuestionType"/></th>
											<th width="22%"><fmt:message key="lms.paperQuestionAmount"/></th>
											<th width="22%"><fmt:message key="lms.paperQuestionScore"/></th>
											<th width="22%"><fmt:message key="lms.operation"/></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="modal-footer" style="padding: 15px 32px 0px 15px;">
<!-- 			<button id="completeBtn" type="button" class="btn blue btn-sm" -->
<!-- 				style="margin-top: 15px;">完成</button> -->
<!-- 			<button id="testBut" type="button" class="btn default btn-sm" -->
<!-- 				data-dismiss="modal" style="margin-top: 15px;">测试</button> -->
			<button id="returnBut" type="button" class="btn default btn-sm"
				data-dismiss="modal" style="margin-top: 15px;"><fmt:message key="lms.return"/></button>
		</div>
	</div>
</body>
<sitemesh:script>
<script type="text/javascript">
var paperId = <%=request.getParameter("paperId")%>;
//条件设置
var fmtSetCondition = '<fmt:message key="lms.setCondition"/>';
//添加试题
var fmtAddPaperQuestion = '<fmt:message key="lms.addPaperQuestion"/>';
//设置题型
var fmtSetPaperQuestion = '<fmt:message key="lms.setPaperQuestion"/>';
//试题列表
var fmtQuestionList = '<fmt:message key="lms.questionList"/>';
//全部
var fmtAll = '<fmt:message key="lms.all"/>';
</script>
<script src="${pageContext.request.contextPath}/js/teacher/newtestpaper/newtestpaperEdit.js?version=${version}"></script>
</sitemesh:script>
</html>