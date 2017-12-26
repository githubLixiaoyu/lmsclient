<%@page import="com.zxtech.auth.util.AuthDetailUtil"%>
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
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.userInfo"/>
					</div>
				</div>
				<div class="portlet-body">
					<input type="hidden" id="studentid" name="studentid"/>
					<input type="hidden" id="loginUserDepartid" value="<%=AuthDetailUtil.getDepartid() %>"/>
					<input type="hidden" id="loginUserDepartname" value="<%=AuthDetailUtil.getDepartname() %>"/>
					<input type="hidden" id="loginUserAuthId" value="<%=AuthDetailUtil.getAuthId() %>"/>
					<table>
						<tr>
							<td rowspan="4">
								<div id="photoDiv" class="d2">
									<div class="d1"><img src="${pageContext.request.contextPath}/images/noimage.jpg" style="width:170px;height:170px;" /></div>
								</div>
							</td>
							<td><label><fmt:message key="lms.loginCode"/>:</label></td>
							<td>
								<input class="form-control" id="logincode" name="logincode" type="text" style="width: 200px;">
							</td>
							<td><label><fmt:message key="lms.dataStatus"/>:</label></td>
							<td>
								<input class="form-control" id="status" name="status" type="text" style="width: 200px;">
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.name"/>:</label></td>
							<td>
								<input class="form-control" id="name" name="name" type="text">
							</td>
							<td><label><fmt:message key="lms.role"/>:</label></td>
							<td>
								<input class="form-control" id="authId" name="authId" type="text">
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.nickName"/>:</label></td>
							<td>
								<input class="form-control" id="nickname" name="nickname" type="text">
							</td>
							<td><label><fmt:message key="lms.newPassword"/>:</label></td>
							<td>
								<input class="form-control" id="newPassword" name="newPassword" type="text">
							</td>
						</tr>
						<tr>
							<td><label><fmt:message key="lms.departName"/>:</label></td>
							<td>
								<input class="form-control" id="departid" name="departid" type="text">
							</td>
<!-- 							<td><label>确认密码:</label></td> -->
<!-- 							<td> -->
<!-- 								<input class="form-control" id="newPassword1" name="newPassword1" type="password"> -->
<!-- 							</td> -->
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
<script src="${pageContext.request.contextPath}/js/manager/permissionsStudentinfo/permissionsStudentinfoEdit_dlg.js?version=${version}"></script>
<script>
//登录用户部门编号
var loginUserDepartid = $("#loginUserDepartid").val();
//登录用户部门名称
var loginUserDepartname = $("#loginUserDepartname").val();
//登录用户角色id
var loginUserAuthId = $("#loginUserAuthId").val();

//用户名
var fmtLoginCode = '<fmt:message key="lms.loginCode"/>';
//姓名
var fmtName = '<fmt:message key="lms.name"/>';
//状态
var fmtDataStatus = '<fmt:message key="lms.dataStatus"/>';
//角色
var fmtRole = '<fmt:message key="lms.role"/>';
//昵称
var fmtNickName = '<fmt:message key="lms.nickName"/>';
//密码
var fmtPassword = '<fmt:message key="lms.password"/>';
//部门名称
var fmtDepartName = '<fmt:message key="lms.departName"/>';
//不能为空
var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
</script>
</sitemesh:script>
</html>