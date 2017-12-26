<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><fmt:message key="lms.courseMaintenance"/></title>
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
					<i class="fa fa-building"></i><fmt:message key="lms.courseMaintenance"/>
					</div>
				</div>
				<div class="portlet-body myform">
					<div id="searchDiv">
						<form id="searchForm" action="#">
							<div class="form-body">
								<div class="table-responsive">
									<table class="table">
										<tbody>
											<tr>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.courseName"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="coursename" name="coursename" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
                                                <td class="formlable" width="8.5%">
													<fmt:message key="lms.courseFirstTypeName"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="flag" name="flag" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
 												<td class="formlable" width="8.5%">
													<fmt:message key="lms.courseSecTypeName"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="coursesectype" name="coursesectype" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
 											</tr>
 											<tr>
 												<td class="formlable" width="8.5%">
													<fmt:message key="lms.courseUploadType"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="courseuploadtype" name="courseuploadtype" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
 												<td class="formlable" width="8.5%">
													<fmt:message key="lms.dataStatus"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="active" name="active" class="form-control input-sm select2" type="text" style="width: 200px;"/>
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
					<div class="caption" id="typeCaption"><fmt:message key="lms.courseList"/>
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
						<button type="button" id="sendBtn" class="btn btn-success">
							<i class="fa fa-upload"></i> <fmt:message key="lms.publish"/>
						</button>
						<button type="button" id="viewCommentBtn" class="btn btn-success">
							<i class="fa fa-upload"></i> <fmt:message key="lms.viewComment"/>
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
											<th width="20%"><fmt:message key="lms.courseName"/></th>
											<th width="5%"><fmt:message key="lms.courseTime"/></th>
											<th width="10%"><fmt:message key="lms.courseFirstTypeName"/></th>
											<th width="10%"><fmt:message key="lms.courseSecTypeName"/></th>
											<th width="10%"><fmt:message key="lms.courseUploadType"/></th>
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
<script src="${pageContext.request.contextPath}/js/manager/courseInfo/courseInfoList.js?version=${version}"></script>
<script>
//课件类型 课件
var fmtCourseUploadType1 = '<fmt:message key="lms.courseUploadType1"/>';
//课件类型 三维
var fmtCourseUploadType2 = '<fmt:message key="lms.courseUploadType2"/>';
//课件类型 视频
var fmtCourseUploadType3 = '<fmt:message key="lms.courseUploadType3"/>';
//状态 正在创建
var fmtDataStatus2 = '<fmt:message key="lms.dataStatus2"/>';
//状态 已发布
var fmtDataStatus3 = '<fmt:message key="lms.dataStatus3"/>';
//不能为空
var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
//课程新增
var fmtCourseAdd = '<fmt:message key="lms.courseAdd"/>';
//课程编辑
var fmtCourseEdit = '<fmt:message key="lms.courseEdit"/>';
//请选择
var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
</script>
</sitemesh:script>
</html>