<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title><fmt:message key="lms.questionInfo"/></title>
<style type="text/css">
 table label{
 	float: right;
 }
 td{
 	padding:5px;
 }
 .txt{ 
	border-bottom:1px solid #000000; /* 下划线效果 */ 
	border-top:0px; 
	border-left:0px; 
	border-right:0px; 
	background-color:transparent; /* 背景色透明 */ 
} 
</style>

</head>
<body>
<div class="modal fade" id="portlet-upload-dlg" role="basic" aria-hidden="true">
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
				</div>
				<div class="portlet-body">
				<input id="questionsid" name="questionsid" type="hidden" value="<%=request.getParameter("questionsid")%>">
					<table>
						<tr>
							<td>
								<label>
									<fmt:message key="lms.questionType"/>:<span style="color:red;padding-left:5px;">*</span>
								</label>
							</td>
							<td><input class="form-control" id="typeid" name="typeid" type="text" style="width: 200px;"></td>
							<td style="padding-left:30px;">
								<label>
									<fmt:message key="lms.questionDifficulty"/>:<span style="color:red;padding-left:5px;">*</span>
								</label>
							</td>
							<td><input class="form-control" id="difficultyId" name="difficultyId" type="text" style="width: 200px;"></td>
						</tr>
						<tr>
							<td>
								<label>
									<fmt:message key="lms.questionCategory"/>:<span style="color:red;padding-left:5px;">*</span>
								</label>
							</td>
							<td><input class="form-control" id="categoryid" name="categoryid" type="text" style="width: 200px;"></td>
							<td>
								<label><fmt:message key="lms.knowledge"/>:</label>
							</td>
							<td><input class="form-control" id="point" name="point" type="text" style="width: 200px;"></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.questionContent"/>
					</div>
					<div class="actions" style="float: right;">
						<input type="button" id="addStemBtn" class="btn btn-success" value="<fmt:message key="lms.newAdd"/>">
					</div>
				</div>
				<div class="portlet-body">
					<div id="stemContent"></div>
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin" id="questionBlank" style="display: none;">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.referenceAnswer"/>
					</div>
				</div>
				<div class="portlet-body">
					<input type='text' size='13' name='quesBlanks' id='i0' class='txt' class="form-control"/>
					<img src='<%=request.getContextPath() %>/images/001.gif' style='cursor: hand;' alt='<fmt:message key="lms.newAdd"/>' width='14' height='14' onclick='addAnswer();' />&nbsp;
					<img src='<%=request.getContextPath() %>/images/083.gif' style='cursor: hand;' alt='<fmt:message key="delete"/>' width='14' height='14' onclick='moveAnswer();' />
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin" id="questionChoice" style="display: none;">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.selectionInfo"/>
					</div>
					<div class="actions" style="float: right;">
						<input type="button" id="addOptionBtn" class="btn btn-success" value="<fmt:message key="lms.newAdd"/>">
					</div>
				</div>
				<div class="portlet-body">
					<table id="optionTable"></table>
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin" id="questionJudge" style="display: none;">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.referenceAnswer"/>
					</div>
				</div>
				<div class="portlet-body">
					<div id="judgeContent"></div>
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin" id="questionEssay" style="display: none;">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.referenceAnswer"/>
					</div>
					<div class="actions" style="float: right;">
						<input type="button" id="addEssayBtn" class="btn btn-success" value="<fmt:message key="lms.newAdd"/>">
					</div>
				</div>
				<div class="portlet-body">
					<div id="essayContent"></div>
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin" id="questionAnalysis">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.questionAnalysis"/>
					</div>
					<div class="actions" style="float: right;">
						<input type="button" id="addAnalysisBtn" class="btn btn-success" value="<fmt:message key="lms.newAdd"/>">
					</div>
				</div>
				<div class="portlet-body">
					<div id="analysisContent"></div>
				</div>
			</div>
		</div>

		<div class="modal-footer" style="padding: 15px 32px 0px 15px;">
			<button id="saveBtn" type="button" class="btn blue btn-sm"
				style="margin-top: 15px;"><fmt:message key="lms.save"/></button>
			<button id="cancelBtn" type="button" class="btn default btn-sm"
				data-dismiss="modal" style="margin-top: 15px;"><fmt:message key="lms.close"/></button>
		</div>
	</div>
</body>
<sitemesh:script>
<script src="${pageContext.request.contextPath}/js/teacher/question/questionsinfoEdit.js?version=${version}"></script>
<script>
//正确
var fmtCorrect = '<fmt:message key="lms.correct"/>';
//错误
var fmtFault = '<fmt:message key="lms.fault"/>';
//题型
var fmtQuestionType = '<fmt:message key="lms.questionType"/>';
//试题难度
var fmtQuestionDifficulty = '<fmt:message key="lms.questionDifficulty"/>';
//试题分类
var fmtQuestionCategory = '<fmt:message key="lms.questionCategory"/>';
//题干内容
var fmtQuestionContent = '<fmt:message key="lms.questionContent"/>';
//选项内容
var fmtSelectionContent = '<fmt:message key="lms.selectionContent"/>';
//参考内容
var fmtReferenceContent = '<fmt:message key="lms.referenceContent"/>';
//解析内容
var fmtAnalysisContent = '<fmt:message key="lms.analysisContent"/>';
//参考答案
var fmtReferenceAnswer = '<fmt:message key="lms.referenceContent"/>';
//不能为空
var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
//请选择
var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
//编辑
var fmtEdit = '<fmt:message key="lms.edit"/>';
//删除
var fmtDelete = '<fmt:message key="lms.delete"/>';
</script>
</sitemesh:script>
</html>