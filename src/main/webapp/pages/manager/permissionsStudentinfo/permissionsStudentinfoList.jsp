<%@page import="com.zxtech.auth.util.AuthDetailUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><fmt:message key="lms.userMaintenance"/></title>
	<style type="text/css">
		#searchForm td {
			white-space:nowrap; 
		}
		tbody td{
			padding-left: 5px !important;
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
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue-hoki nomargin">
				<div class="portlet-title">
					<div class="caption">
					<i class="fa fa-building"></i><fmt:message key="lms.userMaintenance"/>
					</div>
				</div>
				<input type="hidden" id="loginUserDepartid" value="<%=AuthDetailUtil.getDepartid() %>"/>
				<input type="hidden" id="loginUserDepartname" value="<%=AuthDetailUtil.getDepartname() %>"/>
				<input type="hidden" id="loginUserAuthId" value="<%=AuthDetailUtil.getAuthId() %>"/>
				<div class="portlet-body myform">
					<div id="searchDiv">
						<form id="searchForm" action="#">
							<div class="form-body">
								<div class="table-responsive">
									<table class="table">
										<tbody>
											<tr>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.departName"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="departid" name="departid" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.loginCode"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="logincode" name="logincode" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.name"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="name" name="name" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
												
 											</tr>
 											<tr>
 												<td class="formlable" width="8.5%">
													<fmt:message key="lms.dataStatus"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="status" name="status" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
 												<td class="formlable" width="8.5%">
													<fmt:message key="lms.role"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="authId" name="authId" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
                                                <td class="formbody" width="10.7%" colspan="5">
													<span class="input-group-btn btn-sm">
														<button id="searchBtn" class="btn green-haze" type="button" style="height: 30px;top: 1px;font-size: 10pt;" ><i class="glyphicon glyphicon-search"></i> <fmt:message key="lms.search"/> </button>
													</span>
												</td>
 											</tr>						
										</tbody>
									</table>
								</div>
							</div>
	 					</form>
					</div>
				</div>
			</div>
			<div class="portlet box blue-hoki nomargin ">
				<div class="portlet-title">
					<div class="caption" id="typeCaption"><fmt:message key="lms.userList"/>
					</div>
					<div class="actions">
						<button type="button" id="addBtn" class="btn btn-success">
							<i class="fa fa-plus"></i> <fmt:message key="lms.add"/>
						</button>
						<button type="button" id="editBtn" class="btn btn-success">
							<i class="fa fa-edit"></i> <fmt:message key="lms.edit"/>
						</button>
						<button type="button" id="removeBtn" class="btn btn-success">
							<i class="fa fa-trash-o"></i> <fmt:message key="lms.delete"/>
						</button>
					</div>
				</div>
				<div class="portlet-body myform nopadding">
					<div class="portlet-body">
						<div class="table-responsive">
							<div class="tabbable-custom ">
								<table id="listTable" class="table table-hover">
									<thead>
										<tr>
											<th width="5%"><input type="checkbox" class="group-checkable" id="checkAll"></th>
											<th width="15%"><fmt:message key="lms.loginCode"/></th>
											<th width="15%"><fmt:message key="lms.name"/></th>
											<th width="20%"><fmt:message key="lms.nickName"/></th>
											<th width="15%"><fmt:message key="lms.departName"/></th>
											<th width="10%"><fmt:message key="lms.role"/></th>
											<th width="10%"><fmt:message key="lms.createTime"/></th>
											<th width="5%"><fmt:message key="lms.dataStatus"/></th>
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
	</div>
	
</body>
<sitemesh:script>
<script src="${pageContext.request.contextPath}/js/manager/permissionsStudentinfo/permissionsStudentinfoList.js?version=${version}"></script>
<script>
//登录用户部门编号
var loginUserDepartid = $("#loginUserDepartid").val();
//登录用户部门名称
var loginUserDepartname = $("#loginUserDepartname").val();
//登录用户角色id
var loginUserAuthId = $("#loginUserAuthId").val();
//状态 激活
var fmtUserStatus0 = '<fmt:message key="lms.userStatus0"/>';
//状态 注销
var fmtUserStatus1 = '<fmt:message key="lms.userStatus1"/>';
//状态 已删除
var fmtUserStatus2 = '<fmt:message key="lms.userStatus2"/>';
//用户新增
var fmtUserAdd = '<fmt:message key="lms.userAdd"/>';
//用户编辑
var fmtUserEdit = '<fmt:message key="lms.userEdit"/>';
//请选择
var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
</script>
</sitemesh:script>
</html>