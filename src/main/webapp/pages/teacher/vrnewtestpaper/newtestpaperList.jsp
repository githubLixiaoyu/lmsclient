<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="i18n/msg" />
<!DOCTYPE html>
<html lang="en">
<head>
<title><fmt:message key="lms.testpaperMaintenance" /></title>
<style type="text/css">
#searchForm td {
	white-space: nowrap;
}

tbody td {
	padding-left: 5px !important;
}
</style>
</head>
<body>
	<div class="modal fade" id="portlet-upload-dlg" tabindex="-1"
		role="basic" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">Modal Title</h4>
				</div>
				<div class="modal-body">Modal body goes here</div>
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
						<i class="fa fa-building"></i>
						<fmt:message key="lms.testpaperMaintenance" />
					</div>
					<input type="hidden" id="checkSend" value="1">
				</div>
				<div class="portlet-body myform">
					<div id="searchDiv">
						<form id="searchForm" action="#">
							<div class="form-body">
								<div class="table-responsive">
									<table class="table">
										<tbody>
											<tr>
												<td class="formlable" width="8.5%"><fmt:message
														key="lms.testpaperName" /></td>
												<td class="formbody" width="15%"><input
													id="mileStoneName" name="mileStoneName"
													class="form-control" type="text" style="width: 300px;" /></td>

												<td class="formlable" width="8.5%"><fmt:message
														key="lms.dataStatus" /></td>
												<td class="formbody" width="15%" colspan="3"><input
													id="statusTypeId" name="statusTypeId"
													class="form-control input-sm select2" type="text"
													style="width: 300px;" /></td>
												

											</tr>
											<tr>
												<td class="formlable" width="8.5%"><fmt:message
														key="lms.testTemplate" /></td>
												<td class="formbody" width="15%"><input
													id="testTemplateId" name="testTemplateId"
													class="form-control  select2" type="text"
													style="width: 300px;" /></td>
												<td class="formlable" width="8.5%"><fmt:message
														key="lms.testType" /></td>
												<td class="formbody" width="15%"><input id="openType"
													name="openType" class="form-control  select2" type="text"
													style="width: 300px;" /></td>
												<td width="10%"><span
													class="input-group-btn btn-sm">
														<button id="searchBtn" class="btn green-haze"
															type="button"
															style="height: 30px; top: 1px; font-size: 10pt;">
															<i class="glyphicon glyphicon-search"></i>
															<fmt:message key="lms.search" />
														</button>
												</span></td>
												<td class="formbody" width="10.7%"></td>
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
					<div class="caption" id="typeCaption">
						<fmt:message key="lms.testpaperList" />
					</div>
					<div class="actions">
						<button type="button" id="addBtn" class="btn btn-success">
							<i class="fa fa-plus"></i>
							<fmt:message key="lms.add" />
						</button>
						<button type="button" id="editBtn" class="btn btn-success">
							<i class="fa fa-edit"></i>
							<fmt:message key="lms.edit" />
						</button>
						<button type="button" id="removeBtn" class="btn btn-success">
							<i class="fa fa-trash-o"></i>
							<fmt:message key="lms.forbidden" />
						</button>
						<button type="button" id="sendBtn" class="btn btn-success">
							<i class="fa fa-plus"></i>
							<fmt:message key="lms.publish" />
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
											<th width="5%"><input type="checkbox"
												class="group-checkable" id="checkAll"></th>
											<th width="10%"><fmt:message key="lms.testpaperName" /></th>
											<th width="15%"><fmt:message key="lms.testTemplate" /></th>
											<th width="15%"><fmt:message key="lms.testType" /></th>
											<th width="10%"><fmt:message key="lms.createUser" /></th>
											<th width="10%"><fmt:message key="lms.createTime" /></th>
											<th width="10%"><fmt:message key="lms.testTime" /></th>
											<th width="10%"><fmt:message key="lms.dataStatus" /></th>

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
	<script
		src="${pageContext.request.contextPath}/js/teacher/vrnewtestpaper/newtestpaperList.js?version=${version}"></script>
	<script>
		var fmtPaperType2 = '<fmt:message key="lms.paperType2"/>';
		//请选择
		var fmtPleaseSelect = '<fmt:message key="lms.pleaseSelect"/>';
		//试卷编辑
		var fmtTestpaperEdit = '<fmt:message key="lms.testpaperEdit"/>';
		//已禁用的状态不能再禁用
		var fmtCheckStatus4  = '<fmt:message key="lms.checkStatus4"/>';
		//PC版
		var fmtPC = '<fmt:message key="lms.pc"/>';
		//VR版
		var fmtVR = '<fmt:message key="lms.vr"/>';
		//HTC简化版
		var fmtHTCsimple = '<fmt:message key="lms.htcsimple"/>';
		//HTC高配版
		var fmtHTChigh = '<fmt:message key="lms.htchigh"/>';
		
	</script>
</sitemesh:script>
</html>