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
/*  	float: right; */
 }
 td{
 	padding:5px;
 }
</style>

</head>
<body>
	<div>
		<div class="col-md-12" style="overflow-y: auto;overflow-x: hidden; height: 350px">
		
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.setCondition"/>
					</div>
				</div>
				<div class="portlet-body">
					<input type="hidden" id="id" name="id"/>
					<input type="hidden" id="paperid" name="paperid"/>
					<input type="hidden" id="typeid" name="typeid"/>
					<table>
						<tr>
							<td><label><fmt:message key="lms.questionCategory"/>:</label></td>
							<td><input class="form-control" id="categoryid" name="categoryid" type="text" style="width: 200px;"></td>
							<td><label><fmt:message key="lms.knowledge"/>:</label></td>
							<td><input class="form-control" id="knowledgepoint" name="knowledgepoint" type="text" style="width: 200px;"></td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.testpaperSetupMode"/>:</label></td>
							<td colspan="3">
								<div class="radio-list" style="margin-left: 30px;">
									<label class="radio-inline">
										<input id="addtype1" name="addtype" type="radio" value="1" checked="checked"><fmt:message key="lms.testpaperTotalSetup"/>
									</label>
									<label class="radio-inline">
										<input id="addtype2" name="addtype" type="radio" value="0"><fmt:message key="lms.testpaperDetailSetup"/>
									</label>
								</div>
							</td>
						</tr>
						<tr class="setAll">
							<td></td>
							<td>
								<label><fmt:message key="lms.questionAmount"/>:</label>
								<input class="form-control" id="questionSummaryNum" name="questionSummaryNum" type="text" style="width: 50px;display: inline;" value="0" onkeyup="checkNum(this)">/
								<span id="questionSummaryNumStr" style="color:red">0</span>
								<fmt:message key="lms.unit"/>
							</td>
							<td colspan="2">
								<label><fmt:message key="lms.everyQuestion"/>:</label>
								<input class="form-control" id="questionSummaryScore" name="questionSummaryScore" type="text" style="width: 50px;display: inline;" value="0" onkeyup="checkNum(this)">
								<fmt:message key="lms.questionScore"/> 
							</td>
						</tr>
						<tr class="setDetail">
							<td></td>
							<td>
								<label><fmt:message key="lms.sameScore"/>：<input id="sameScore" type="checkbox" style="padding-left: 15px;"></label>
							</td>
						</tr>
						<tr class="setDetail">
							<td></td>
							<td>
								<label><fmt:message key="lms.easyQuestionAmount"/></label>
								<input class="form-control" id="questionNum1" type="text" style="width: 50px;display: inline;" value="0" onkeyup="checkNum(this)">/
								<span id="questionNumStr1" style="color:red">0</span>
								<fmt:message key="lms.unit"/>
							</td>
							<td colspan="2">
								<label><fmt:message key="lms.everyQuestion"/></label>
								<input class="form-control" id="questionScore1" type="text" style="width: 50px;display: inline;" value="0" onkeyup="checkNum(this)">
								<fmt:message key="lms.questionScore"/> 
							</td>
						</tr>
						<tr class="setDetail">
							<td></td>
							<td>
								<label><fmt:message key="lms.mediumQuestionAmount"/></label>
								<input class="form-control" id="questionNum2" type="text" style="width: 50px;display: inline;" value="0" onkeyup="checkNum(this)">/
								<span id="questionNumStr2" style="color:red">0</span>
								<fmt:message key="lms.unit"/>
							</td>
							<td colspan="2">
								<label><fmt:message key="lms.everyQuestion"/></label>
								<input class="form-control" id="questionScore2" type="text" style="width: 50px;display: inline;" value="0" onkeyup="checkNum(this)">
								<fmt:message key="lms.questionScore"/> 
							</td>
						</tr>
						<tr class="setDetail">
							<td></td>
							<td>
								<label><fmt:message key="lms.difficultyQuestionAmount"/></label>
								<input class="form-control" id="questionNum3" type="text" style="width: 50px;display: inline;" value="0" onkeyup="checkNum(this)">/
								<span id="questionNumStr3" style="color:red">0</span>
								<fmt:message key="lms.unit"/>
							</td>
							<td colspan="2">
								<label><fmt:message key="lms.everyQuestion"/></label>
								<input class="form-control" id="questionScore3" type="text" style="width: 50px;display: inline;" value="0" onkeyup="checkNum(this)">
								<fmt:message key="lms.questionScore"/> 
							</td>
						</tr>
					</table>
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
<script src="${pageContext.request.contextPath}/js/teacher/newtestpaper/randompaperDifficultyEdit_dlg.js?version=${version}"></script>
<script>
//试题难易程度
var difficultyMap = {};
//获取父页面对象
var winPar = window.parent;
//所有题型
var fmtAllTypesRemarkInfo = '<fmt:message key="lms.allTypesRemarkInfo"/>';
//简单题
var fmtEasyQuestionRemarkInfo = '<fmt:message key="lms.easyQuestionRemarkInfo"/>';
//中等题
var fmtMediumQuestionRemarkInfo = '<fmt:message key="lms.mediumQuestionRemarkInfo"/>';
//困难题
var fmtDifficultyQuestionRemarkInfo = '<fmt:message key="lms.difficultyQuestionRemarkInfo"/>';
</script>
</sitemesh:script>
</html>