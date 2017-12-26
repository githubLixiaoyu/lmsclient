<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><fmt:message key="lms.courseTypeMaintenance"/></title>
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
					<i class="fa fa-building"></i><fmt:message key="lms.courseTypeMaintenance"/>
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
													<fmt:message key="lms.courseTypeName"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                	<input id="parentid" name="parentid" type="hidden" value="0"/>
                                                    <input id="coursetypename" name="coursetypename" class="form-control input-sm select2" type="text" style="width: 200px;"/>
                                                </td>
												<td class="formlable" width="8.5%">
													<fmt:message key="lms.courseTypeNameEn"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                    <input id="coursetypenameEn" name="coursetypenameEn" class="form-control input-sm select2" type="text" style="width: 200px;"/>
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
					<div class="caption" id="typeCaption"><fmt:message key="lms.firstCategoryList"/>
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
					</div>
				</div>
				<div class="portlet-body myform nopadding">
					<div class="portlet-body">
						<div class="table-responsive">
							<div class="tabbable-custom ">
								<table id="courseTypeTable" class="table table-hover">
									<thead>
										<tr>
											<th width="5%"><input type="checkbox" class="group-checkable" id="checkAll"></th>
											<th width="15%"><fmt:message key="lms.courseTypeName"/></th>
											<th width="15%"><fmt:message key="lms.courseTypeNameEn"/></th>
											<th width="15%"><fmt:message key="lms.maintenanceTime"/></th>
											<th width="7%"><fmt:message key="lms.orderNum"/></th>
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
<script src="${pageContext.request.contextPath}/js/manager/courseType/courseTypeList.js?version=${version}"></script>
<script>
//一级分类列表
var fmtFirstCategoryList = '<fmt:message key="lms.firstCategoryList"/>';
//二级分裂列表
var fmtSecondCategoryList = '<fmt:message key="lms.secondCategoryList"/>';
//一级分类新增
var fmtFirstCategoryAdd = '<fmt:message key="lms.firstCategoryAdd"/>';
//二级分类新增
var fmtSecondCategoryAdd = '<fmt:message key="lms.secondCategoryAdd"/>';
//一级分类编辑
var fmtFirstCategoryEdit = '<fmt:message key="lms.firstCategoryEdit"/>';
//二级分类编辑
var fmtSecondCategoryEdit = '<fmt:message key="lms.secondCategoryEdit"/>';
//课程名称
var fmtCourseName = '<fmt:message key="lms.courseName"/>';
</script>
</sitemesh:script>
</html>