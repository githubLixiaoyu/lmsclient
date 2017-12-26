<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/pages/common/fmtLanguage.jsp"%>
<fmt:setBundle basename="i18n/msg" />
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
<style type="text/css">
table label {
	float: right;
}

td {
	padding: 5px;
}
</style>

</head>
<body>
<span class="bgstretcher-login-id-new"><a class="bgstretcher-login-id-new-a" href="javascript:void(registerUser())"><fmt:message key="lms.joinFreeNow"/></a></span>

	<div>
		<div class="portlet-body myform nopadding">
			<div class="portlet-body">
				<div class="table-responsive">
					<div class="tabbable-custom ">
						<table id="listTable" class="table table-hover">
							<thead>
								<tr>
									<th width="5%"><input type="checkbox"
										class="group-checkable" id="checkAll"></th>
									<th width="20%"><fmt:message key="lms.courseName" /></th>
									<th width="5%"><fmt:message key="lms.courseTime" /></th>
									<th width="10%"><fmt:message key="lms.courseFirstTypeName" /></th>
									<th width="10%"><fmt:message key="lms.courseSecTypeName" /></th>
									<th width="10%"><fmt:message key="lms.courseUploadType" /></th>
									<th width="10%"><fmt:message key="lms.createTime" /></th>
									<th width="5%"><fmt:message key="lms.dataStatus" /></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

<%-- 		<div class="modal-footer" style="padding: 15px 32px 0px 15px;">
			<button id="saveBtn" type="button" class="btn blue btn-sm"
				style="margin-top: 15px;">
				<fmt:message key="lms.save" />
			</button>
			<button id="baseWinFalseBut" type="button" class="btn default btn-sm"
				data-dismiss="modal" style="margin-top: 15px;">
				<fmt:message key="lms.close" />
			</button>
		</div> --%>
	</div>
</body>
<sitemesh:script>
	<script
		src="${pageContext.request.contextPath}/js/manager/permissionsStudentDepart/permissionsStudentDepartAssignCourse_dlg.js?version=${version}"></script>
	<script>
		//公司名称
		var fmtCompanyName = '<fmt:message key="lms.companyName"/>';
		//不能为空
		var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
	</script>
</sitemesh:script>
</html>