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
			<div class="portlet box blue-hoki nomargin ">
				<div class="portlet-body myform nopadding">
					<div class="portlet-body">
						<div class="table-responsive">
							<div class="tabbable-custom ">
								<table id="listHistoryTable" class="table table-hover">
									<thead>
										<tr>
											<th width="10%"><fmt:message key="lms.platformType"/></th>
											<th width="10%"><fmt:message key="lms.versionNum"/></th>
											<th width="30%"><fmt:message key="lms.fileName"/></th>
											<th width="15%"><fmt:message key="lms.mustUpdate"/></th>
											<th width="15%"><fmt:message key="lms.createTime"/></th>
											<th width="15%"><fmt:message key="lms.createUser"/></th>
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
		<div class="modal-footer" style="padding: 15px 32px 0px 15px;">
			<button id="baseWinFalseBut" type="button" class="btn default btn-sm"
				data-dismiss="modal" style="margin-top: 15px;"><fmt:message key="lms.close"/></button>
		</div>
</body>
<sitemesh:script>
<script src="${pageContext.request.contextPath}/js/manager/app/appByPlatformList_dlg.js?version=${version}"></script>
</sitemesh:script>
</html>