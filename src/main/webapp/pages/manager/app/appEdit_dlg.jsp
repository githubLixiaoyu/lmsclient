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
</style>

</head>
<body>
	<div>
		<div class="col-md-12" style="overflow-y: auto;overflow-x: hidden; height: 350px">
		
			<div class="portlet box blue-hoki">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.versionInfo"/>
					</div>
				</div>
				<div class="portlet-body">
					<input type="hidden" id="id" name="id"/>
					<input type="hidden" id="parentid" name="parentid" value="0"/>
					<table id="editTable">
						<tr>
							<td><label><fmt:message key="lms.platformType"/>:<font style="color: red;"> * </font></label></td>
							<td><input class="form-control" id="platform" name="platform" type="text" style="width: 200px;"></td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.mustUpdate"/>:<font style="color: red;"> * </font></label></td>
							<td><input class="form-control" id="mustUpdate" name="mustUpdate" type="text" style="width: 200px;"></td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.versionNum"/>:<font style="color: red;"> * </font></label></td>
							<td><input class="form-control" id="versionCode" name="versionCode" type="text" style="width: 200px;" onkeyup="checkNum(this)"></td>
						</tr>
						<tr>
							<td><label><font style="color: red;"> * </font></label></td>
							<td>
								<input type="file" id="fileUpload" name="file" onchange="fileUploadChange('fileUpload','filename')"/>
								<input type="hidden" id="filename" name="filename"/>
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
<script src="${pageContext.request.contextPath}/js/manager/app/appEdit_dlg.js?version=${version}"></script>
<script>
</script>
</sitemesh:script>
</html>