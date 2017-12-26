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
						<i class="fa fa-bookmark"></i>&nbsp;<fmt:message key="lms.companyInfo"/>
					</div>
				</div>
				<div class="portlet-body">
					<input type="hidden" id="departid" name="departid"/>
					<table>
						<tr>
							<td><label><fmt:message key="lms.companyName"/>:</label></td>
							<td>
								<input class="form-control" id="departname" name="departname" type="text" style="width: 200px;">
							</td>
							<td width="200"></td>
							<td width="200"></td>
						</tr>
						<tr>
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
<script src="${pageContext.request.contextPath}/js/manager/permissionsStudentDepart/permissionsStudentDepartEdit_dlg.js?version=${version}"></script>
<script>
//公司名称
var fmtCompanyName = '<fmt:message key="lms.companyName"/>';
//不能为空
var fmtIsNotNull = "<fmt:message key='lms.isNotNull'/>";
</script>
</sitemesh:script>
</html>