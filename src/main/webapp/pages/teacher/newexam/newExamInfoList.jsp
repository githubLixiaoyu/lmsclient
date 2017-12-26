<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><fmt:message key="lms.examMaintenance"/></title>
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
					<i class="fa fa-building"></i><fmt:message key="lms.examMaintenance"/>
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
													<fmt:message key="lms.examName"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="examname" name="examname" class="form-control" type="text" style="width: 200px;"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.examCategory"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="examcategroy" name="examcategroy" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.examType"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="examtype" name="examtype" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
 											</tr>
 											<tr>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.dataStatus"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="status" name="status" class="form-control input-sm select2" type="text" style="width: 200px;"/>
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
					<div class="caption" id="typeCaption"><fmt:message key="lms.examInfoList"/>
					</div>
					<div class="actions">
						<button type="button" id="returnBtn" class="btn btn-success" style="display: none">
							<i class="fa fa-arrow-left"></i> <fmt:message key="lms.return"/>
						</button>
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
							<i class="fa fa-plus"></i> <fmt:message key="lms.publish"/>
						</button>
						<button type="button" id="addUser" class="btn btn-success">
							<i class="fa fa-plus"></i> <fmt:message key="lms.addUser"/>
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
											<th width="25%"><fmt:message key="lms.examName"/></th>
											<th width="8%"><fmt:message key="lms.examCategory"/></th>
											<th width="8%"><fmt:message key="lms.usePaperName"/></th>
											<th width="8%"><fmt:message key="lms.examType"/></th>
											<th width="8%"><fmt:message key="lms.dataStatus"/></th>
											<th width="13%"><fmt:message key="lms.createTime"/></th>
											<th width="8%"><fmt:message key="lms.createUser"/></th>
											<th width="8%"><fmt:message key="lms.operation"/></th>
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
<script src="${pageContext.request.contextPath}/js/teacher/newexam/newExamInfoList.js?version=${version}"></script>
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
//查看学生考试结果
var fmtViewExamResult = '<fmt:message key="lms.viewExamResult"/>';
//试题编辑
var fmtQuestionEdit = '<fmt:message key="lms.questionEdit"/>';
</script>
</sitemesh:script>
</html>