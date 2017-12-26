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
 table label{
 	float: right;
 }
 td{
 	padding:5px;
 }
 label .radioCss{
	width: 20px !important;
	margin-top: -1px;
 }
</style>

</head>
<body>
	<div>
		<div class="col-md-12" style="overflow-y: auto;overflow-x: hidden; height: 350px">
		
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.courseInfo"/>
					</div>
				</div>
				<div class="portlet-body">
					<input type="hidden" id="courseid" name="courseid"/>
					<input type="hidden" id="imageId" name="imageId"/>
					<table id="courseTable">
						<tr>
							<td class="scormTd"><label><fmt:message key="lms.localSCORM"/>：</label></td>
							<td class="scormTd">
								<input type="file" id="myfile" name="file" onchange="fileUploadScormChange('myfile','fileScormName')"/>
								<input type="hidden" id="fileScormName" name="fileScormName"/>
								<font color="red"><span id="sp1"> * <fmt:message key="lms.fileType"/>：.zip</span></font>
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.courseName"/>：<font color="red"><span id="sp1"> * </span></font></label></td>
							<td>
								<input class="form-control" id="coursename" name="coursename" type="text" style="width: 200px;">
							</td>
							<td><label><fmt:message key="lms.courseTime"/>(h)：<font color="red"><span id="sp1"> * </span></font></label></td>
							<td>
								<input class="form-control" id="period" name="period" type="text" style="width: 200px;">
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.courseFirstTypeName"/>：<font color="red"><span id="sp1"> * </span></font></label></td>
							<td>
								<input class="form-control" id="flag" name="flag" type="text">
							</td>
							<td><label><fmt:message key="lms.courseSecTypeName"/>：<font color="red"><span id="sp1"> * </span></font></label></td>
							<td>
								<input class="form-control" id="coursesectype" name="coursesectype" type="text">
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.courseUploadType"/>：<font color="red"><span id="sp1"> * </span></font></label></td>
							<td>
								<input class="form-control" id="courseuploadtype" name="courseuploadtype" type="text">
							</td>
							<td><label><fmt:message key="lms.forPeople"/>：</label></td>
							<td>
								<input class="form-control" id="forpeople" name="forpeople" type="text">
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.ifFreeCourse"/>：</label></td>
							<td style="float: left; ">
							</td>
							<td><label><fmt:message key="lms.useTest"/>：</label></td>
							<td style="float: left; ">
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.keyWords"/>：</label></td>
							<td colspan="3">
								<input class="form-control" id="keywords" name="keywords" type="text">
							</td>
						</tr>
						<tr>
							<td><label>ESP KEY：</label></td>
							<td colspan="3">
								<input class="form-control" id="espkey" name="espkey" type="text" disabled="disabled">
							</td>
						</tr>
						<tr id="parentIdDiv">
							<td><label><fmt:message key="lms.webPic"/>：<font color="red"><span id="sp1"> * </span></font></label></td>
							<td>
								<div id="imageFileDiv" class="d2">
									<div class="d1"><img src="${pageContext.request.contextPath}/images/noimage.jpg" style="width:170px;height:170px;" /></div>
								</div>
								<input type="file" id="imageFileUpload" name="file" onchange="fileUploadChange('imageFileUpload','imageFileDiv','imagename')" style="width: 170px;display: none;"/>
								<input type="hidden" id="imagename" name="imagename"/>
								<div style="margin: 8px;">
									<button type="button" onclick="clickFile('imageFileUpload')" class="btn btn-success">
										<fmt:message key="lms.uploadPic"/>
									</button>
								</div>
							</td>
							<td><label><fmt:message key="lms.mobilePic"/>：</label></td>
							<td>
								<div id="imageMobileFileDiv" class="d2">
									<div class="d1"><img src="${pageContext.request.contextPath}/images/noimage.jpg" style="width:170px;height:170px;" /></div>
								</div>
								<input type="file" id="imageMobileFileUpload" name="file" onchange="fileUploadChange('imageMobileFileUpload','imageMobileFileDiv','imagemobilename')" style="width: 170px;display: none;"/>
								<input type="hidden" id="imagemobilename" name="imagemobilename"/>
								<div style="margin: 8px;">
									<button type="button" onclick="clickFile('imageMobileFileUpload')" class="btn btn-success">
										<fmt:message key="lms.uploadPic"/>
									</button>
								</div>
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.remarks"/>：</label></td>
							<td colspan="3">
								<textarea id="remarks" name="remarks" style="width: 100%; height: 60px;" class="form-control"></textarea>
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
<script src="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-file-upload/js/ajaxfileupload.js?version=${version}"></script>
<%-- <link href="${pageContext.request.contextPath}/bootstrap/assets/global/plugins/jquery-file-upload/css/ajaxfileupload.css" rel="stylesheet"/> --%>
<script src="${pageContext.request.contextPath}/js/manager/courseInfo/courseInfoEdit_dlg.js?version=${version}"></script>
<script>
//视频文件
var fmtVideoFile = '<fmt:message key="lms.videoFile"/>';
//是
var fmtYes = '<fmt:message key="lms.updateYes"/>';
//否
var fmtNo = '<fmt:message key="lms.updateNo"/>';
//本地SCORM课件
var fmtLocalSCORM = '<fmt:message key="lms.localSCORM"/>';
//课程名称
var fmtCourseName = '<fmt:message key="lms.courseName"/>';
//课时
var fmtCourseTime = '<fmt:message key="lms.courseTime"/>';
//课程类型
var fmtCourseFirstTypeName = '<fmt:message key="lms.courseFirstTypeName"/>';
//课程分类
var fmtCourseSecTypeName = '<fmt:message key="lms.courseSecTypeName"/>';
//课件类型
var fmtCourseUploadType = '<fmt:message key="lms.courseUploadType"/>';
//WEB端图片
var fmtWebPic = '<fmt:message key="lms.webPic"/>';
//移动端图片
var fmtMobilePic = '<fmt:message key="lms.mobilePic"/>';
//不能为空
var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
//文件以{0}结尾
var fmtCheckFileEndWith = '<fmt:message key="lms.checkFileEndWith"/>';
</script>
</sitemesh:script>
</html>