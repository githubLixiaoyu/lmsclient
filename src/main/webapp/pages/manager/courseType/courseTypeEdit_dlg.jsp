<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title><fmt:message key="lms.courseTypeInfo"/></title>
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
</style>

</head>
<body>
	<div>
		<div class="col-md-12" style="overflow-y: auto;overflow-x: hidden; height: 350px">
		
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.courseTypeInfo"/>
					</div>
				</div>
				<div class="portlet-body">
					<input type="hidden" id="id" name="id"/>
					<input type="hidden" id="parentid" name="parentid" value="0"/>
					<table>
						<tr>
							<td><label><fmt:message key="lms.courseTypeName"/>:</label></td>
							<td><input class="form-control" id="coursetypename" name="coursetypename" type="text" style="width: 200px;"></td>
							<td><label><fmt:message key="lms.orderNum"/>:</label></td>
							<td><input class="form-control" id="sort" name="sort" type="text" style="width: 200px;"></td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.courseTypeNameEn"/>:</label></td>
							<td><input class="form-control" id="coursetypenameEn" name="coursetypenameEn" type="text" style="width: 200px;"></td>
						</tr>
						<tr id="parentIdDiv">
							<td><label><fmt:message key="lms.webPic"/>:</label></td>
							<td>
								<div id="imageFileDiv" class="d2">
									<div class="d1"><img src="${pageContext.request.contextPath}/images/noimage.jpg" style="width:170px;height:170px;" /></div>
								</div>
								<input type="file" id="imageFileUpload" name="file" onchange="fileUploadChange('imageFileUpload','imageFileDiv','imagename')" style="width: 200px;"/>
								<input type="hidden" id="imagename" name="imagename"/>
							</td>
							<td><label><fmt:message key="lms.mobilePic"/>:</label></td>
							<td>
								<div id="imageMobileFileDiv" class="d2">
									<div class="d1"><img src="${pageContext.request.contextPath}/images/noimage.jpg" style="width:170px;height:170px;" /></div>
								</div>
								<input type="file" id="imageMobileFileUpload" name="file" onchange="fileUploadChange('imageMobileFileUpload','imageMobileFileDiv','imagemobilename')" style="width: 200px;"/>
								<input type="hidden" id="imagemobilename" name="imagemobilename"/>
							</td>
						</tr>
						<tr id="remarksDiv">
							<td><label><fmt:message key="lms.remarks"/>:</label></td>
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
<script src="${pageContext.request.contextPath}/js/manager/courseType/courseTypeEdit_dlg.js?version=${version}"></script>
<script>
//分类名称
var fmtCourseTypeName = '<fmt:message key="lms.courseTypeName"/>';
//分类名称英文
var fmtCourseTypeNameEn = '<fmt:message key="lms.courseTypeNameEn"/>';
//WEB端图片
var fmtWebPic = '<fmt:message key="lms.webPic"/>';
//移动端图片
var fmtMobilePic = '<fmt:message key="lms.mobilePic"/>';
//不能为空
var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
//只能输入整数
var fmtCheckInteger = '<fmt:message key="lms.checkInteger"/>';
//排序
var fmtOrderNum = '<fmt:message key="lms.orderNum"/>';
</script>
</sitemesh:script>
</html>