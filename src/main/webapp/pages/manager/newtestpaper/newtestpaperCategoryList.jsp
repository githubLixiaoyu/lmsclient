<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><fmt:message key="lms.testaperCategoryMaintenance"/></title>
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
					<i class="fa fa-building"></i><fmt:message key="lms.testaperCategoryMaintenance"/>
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
													<fmt:message key="lms.testaperCategoryName"/>
                                                </td>
                                                <td class="formbody" width="11.5%">
                                                	<input id="pointsparentid" name="pointsparentid" type="hidden" value="0"/>
                                                    <input id="pointsname" name="pointsname" class="form-control input-sm select2" type="text" style="width: 200px;"/>
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
					<div class="caption" id="typeCaption"><fmt:message key="lms.testaperCategoryList"/>
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
								<table id="listTable" class="table table-hover">
									<thead>
										<tr>
											<th width="5%"><input type="checkbox" class="group-checkable" id="checkAll"></th>
											<th width="15%"><fmt:message key="lms.testaperCategoryName"/></th>
											<th width="60%"><fmt:message key="lms.remarks"/></th>
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
<script src="${pageContext.request.contextPath}/js/manager/newtestpaper/newtestpaperCategoryList.js?version=${version}"></script>
<script>
//试卷分类新增
var fmtTestaperCategoryAdd = '<fmt:message key="lms.testaperCategoryAdd"/>';
//试卷分类二级新增
var fmtTestaperCategorySecondLevelAdd = '<fmt:message key="lms.testaperCategorySecondLevelAdd"/>';
//试卷分类编辑
var fmtTestaperCategoryEdit = '<fmt:message key="lms.testaperCategoryEdit"/>';
//试卷分类二级编辑
var fmtTestaperCategorySecondLevelEdit = '<fmt:message key="lms.testaperCategorySecondLevelEdit"/>';
//试卷分类列表
var fmtTestaperCategoryList = '<fmt:message key="lms.testaperCategoryList"/>';
//试卷分类二级列表
var fmtTestaperCategorySecondLevelList = '<fmt:message key="lms.testaperCategorySecondLevelList"/>';
//试卷分类名称
var fmtTestaperCategoryName = '<fmt:message key="lms.testaperCategoryName"/>';
//不能为空
var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
</script>
</sitemesh:script>
</html>