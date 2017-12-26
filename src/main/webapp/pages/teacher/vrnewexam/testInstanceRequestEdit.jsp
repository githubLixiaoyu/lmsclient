<%@page import="com.zxtech.support.util.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title><fmt:message key="lms.testpaperArrangement"/></title>
<style type="text/css">
 table label{
 	float: left;
 }
 table{
 	margin-left: 15px;
 }
 td{
 	padding:5px;
 }
 .d1{
	 margin: 0px 7px;
	 width:200px;
	 height:200px;
	 border:1px solid #cccccc;
	 padding: 15px;
	 float: left;
 }
 
 .d2{
	 width:200px;
	 height:200px;
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
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.testpaperArrangement"/>
					</div>
				</div>
				<div class="portlet-body">
				<input id="id" name="id" type="hidden" value="<%=request.getParameter("id")%>">
					<table id="formTable">
						<tr>
							<td><label><fmt:message key="lms.examName"/>:<span style="color:red;padding-left:5px;">*</span></label></td>
							<td><input class="form-control" id="mileStoneName" name="mileStoneName" type="text" style="width: 200px;" maxlength="100"></td>
							<td><label><fmt:message key="lms.examType"/>:<span style="color:red;padding-left:5px;">*</span></label></td>
							<td><input class="form-control" id="examType" name="examType" type="text" style="width: 200px;"></td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.startDate"/>:<span style="color:red;padding-left:5px;">*</span></label></td>
							<td><input class="form-control form-control-inline date-picker" id="testStartDate" name="testStartDate" type="text" style="width: 200px;"></td>
							<td><label><fmt:message key="lms.endDate"/>:<span style="color:red;padding-left:5px;">*</span></label></td>
							<td><input class="form-control form-control-inline date-picker" id="testEndDate" name="testEndDate" type="text" style="width: 200px;"></td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.usePaperName"/>:<span style="color:red;padding-left:5px;">*</span></label></td>
							<td><input class="form-control" id="papername" name="papername" type="text" style="width: 200px;" disabled="disabled"></td>
							<td colspan="2">
								<button type="button" id="selectPaperBtn" class="btn btn-success"><fmt:message key="lms.selectPaper"/></button>
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.examCategory"/>:</label></td>
							<td>
								<input class="form-control" id="testName" name="testName" type="text" style="width: 200px;" disabled="disabled">
								<input id="testTemplateId" name="testTemplateId" type="hidden">
								<input id="testTemplateMileStoneId" name="testTemplateMileStoneId" type="hidden">
							</td>
							<td><label><fmt:message key="lms.testSort"/>:</label></td>
							<td><input class="form-control" id="examKind" name="examKind" type="text" style="" maxlength="100"></td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.testTarget"/>:</label></td>
							<td><input class="form-control" id="examTarget" name="examTarget" type="text" style="width: 200px;" maxlength="100"></td>
							<td><label><fmt:message key="lms.testTime"/>:<span style="color:red;padding-left:5px;">*</span></label></td>
							<td><input class="form-control" id="examTime" name="examTime" type="text" style="width: 100px;float: left;" maxlength="8">(<fmt:message key="lms.min"/>)
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.testScore"/>:</label></td>
							<td colspan="2">
<!-- 								<div class="radio-list" style="float: left;display: none;"> -->
<!-- 									<label class="radio-inline"> -->
<%-- 										<input id="examscorestatus1" name="examscorestatus" type="radio" value="0" checked="checked"><fmt:message key="lms.testpaperTotalScore"/> --%>
<!-- 									</label> -->
<!-- 									<label class="radio-inline"> -->
<%-- 										<input id="examscorestatus2" name="examscorestatus" type="radio" value="1"><fmt:message key="lms.passScore"/> --%>
<!-- 									</label> -->
<!-- 								</div> -->
								<input class="form-control" id="paperScore" name="paperScore" type="text" style="width: 100px;float: left;" disabled="disabled">
<!-- 								<input class="form-control" id="definedscore" name="definedscore" type="text" style="width: 100px;float: left;display: none;"> -->
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.passScore"/>:<span style="color:red;padding-left:5px;">*</span></label></td>
							<td colspan="2">
								<div class="radio-list" style="float: left;">
									<label class="radio-inline">
										<input id="passscorestatus1" name="passScoreStatus" type="radio" value="0" checked="checked"><fmt:message key="lms.customScore"/>
									</label>
									<label class="radio-inline">
										<input id="passscorestatus2" name="passScoreStatus" type="radio" value="1"><fmt:message key="lms.inProportion"/>
									</label>
								</div>
							</td>
							<td>
								<input class="form-control" id="definedpassscore" name="definedpassscore" type="text" style="width: 100px;" maxlength="8">
								<div id="percentageDiv" style="display: none;">
									<input class="form-control" id="percentage" name="percentage" type="text" style="width: 100px;">
								</div>
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.uploadPic"/>:</label></td>
							<td colspan="3">
								<div id="imageFileDiv" class="d2" style="float: left;">
									<div class="d1"><img src="${pageContext.request.contextPath}/images/noimage.jpg" style="width:170px;height:170px;" /></div>
								</div>
								<input type="hidden" id="imagename" name="imagename"/>
								<input type="hidden" id="oldImagename" name="oldImagename"/>
								<input style="display: none;" type="file" id="imageFileUpload" name="file" onchange="fileUploadChange('imageFileUpload','imageFileDiv','imagename')"/>
								<div style="float: left;padding-left:20px;padding-top: 165px;">
									<button type="button" id="removeImgBtn" class="btn btn-success" style="display: none;float: left;margin-right: 15px"><fmt:message key="lms.deletePic"/></button>
									<button type="button" id="uploadImgBtn" class="btn btn-success"><fmt:message key="lms.uploadPic"/></button>
								</div>
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.remarks"/>:</label></td>
							<td colspan="3">
								<textarea id="remark" name="remark" style="width: 100%; height: 100px;" class="form-control" maxlength="255"></textarea>
							</td>
						</tr>
					</table>
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
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-file-upload/js/ajaxfileupload.js?version=${version}"></script>
<%-- <link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-file-upload/css/ajaxfileupload.css" rel="stylesheet"/> --%>
<script src="${pageContext.request.contextPath}/js/teacher/vrnewexam/testInstanceRequestEdit.js?version=${version}"></script>
<script>
//考试类型 定期考试
var fmtExamType0 = '<fmt:message key="lms.examType0"/>';
//考试类型 不定期考试
var fmtExamType1 = '<fmt:message key="lms.examType1"/>';
//考试类型 临时考试
var fmtExamType2 = '<fmt:message key="lms.examType2"/>';
//状态 正在创建
var fmtDataStatus2 = '<fmt:message key="lms.dataStatus2"/>';
//状态 已发布
var fmtDataStatus3 = '<fmt:message key="lms.dataStatus3"/>';
//请选择
var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
//试卷列表
var fmtTestpaperList = '<fmt:message key="lms.testpaperList"/>';
</script>
</sitemesh:script>
</html>